/**
 * Copyright 2015 Marco Scavuzzo
 * Contact: Marco Scavuzzo <marco.scavuzzo@polimi.it>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package it.polimi.hegira.api;

import java.io.IOException;
import java.util.List;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.queue.Queue;
import it.polimi.hegira.queue.ServiceQueueMessage;
import it.polimi.hegira.queue.TaskQueue;
import it.polimi.hegira.utils.Constants;
import it.polimi.hegira.utils.DefaultErrors;
import it.polimi.hegira.utils.DefaultSerializer;
import it.polimi.hegira.utils.PropertiesManager;
import it.polimi.hegira.zkWrapper.ZKserver;

import javax.servlet.ServletContextEvent;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * REST API for data migration and synchronization in Hegira 4Clouds.
 * @author Marco Scavuzzo
 */
@Path("/api")
public class API implements javax.servlet.ServletContextListener {
	private transient Logger log = Logger.getLogger(API.class);
	private static Queue queue;
	
	//@GET
	//@Produces({ MediaType.TEXT_PLAIN })
	public String sayPlainTextHello() {
		return "Hello from Hegira!";
	}
	
	//@GET
	//@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(){
		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		
		return "Hello from Hegira!";
	}
	
	//@GET
	//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	//@Path("/publish")
	public Status publish() throws IOException{
		try {
			//Queue queue = new Queue();
			ServiceQueueMessage sqm = new ServiceQueueMessage();
			sqm.setCommand("Ciao Component!");
			byte[] ssqm = DefaultSerializer.serialize(sqm);
			
			queue.publish("SRC", ssqm);
			queue.publish("TWC", ssqm);
			return new Status(Constants.STATUS_SUCCESS);
		} catch (QueueException e) {
			return new Status("ERROR",DefaultErrors.getErrorMessage(DefaultErrors.queueError), DefaultErrors.getErrorNumber(DefaultErrors.queueError));
		}
	}
	
	/**
	 * Instructs the components (SRC and TWC) to perform a complete <b>OFFLINE</b> data switch over from
	 * the source to the destination databases.
	 * This type of data migration is typically not recoverable, i.e., in case of a crash of a component
	 * the data migration can be restarted from scratch. 
	 * @param source The source database identifier (e.g., DATASTORE, TABLES).
	 * @param destination The destination database identifier (e.g., DATASTORE, TABLES).
	 * @param threads The number of TWT (i.e., the number of parallel threads writing towards the target database).
	 * @return A Status object stating whether the command has been properly executed.
	 */
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/switchover")
	public Status switchOver(@QueryParam("source") String source,
							@QueryParam("destination") final List<String> destination,
							@QueryParam("threads") int threads){
		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		//BasicConfigurator.configure();
		//Check input params number
		if(source != null && destination != null && destination.size()>=1){
			//check input content
			boolean source_supported = Constants.isSupported(source);
			List<String> supported_dest = Constants.getSupportedDBfromList(destination);
			if(source_supported && supported_dest.size() >= 1){
				String message = "Mapping from "+source+ " to ";
				StringBuilder sb = new StringBuilder(message);
				for(String sup : supported_dest){
					sb.append(sup+" ");
				}
				
				try {
					if(queue.checkPresence()){
						log.info("Components present");
						ServiceQueueMessage sqm = new ServiceQueueMessage();
						sqm.setCommand("switchover");
						sqm.setSource(source);
						sqm.setDestination(destination);
						sqm.setThreads(threads);
						
						byte[] ssqm = DefaultSerializer.serialize(sqm);
						queue.publish("SRC", ssqm);
						queue.publish("TWC", ssqm);
					}
				} catch (QueueException e) {
					
					return new Status(Constants.STATUS_ERROR, 
							DefaultErrors.getErrorNumber(DefaultErrors.queueError),
							DefaultErrors.getErrorMessage(DefaultErrors.queueError));
				} catch (IOException e) {
					e.printStackTrace();
					return new Status(Constants.STATUS_ERROR, e.getMessage());
				}
				
				
				return new Status(Constants.STATUS_SUCCESS, sb.toString());
			} else {
				//Cannot switchover - Not supported databases
				return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.databaseNotSupported),
									DefaultErrors.getErrorNumber(DefaultErrors.databaseNotSupported));
			}
		} else{
			//Cannot switchover - Few parameters
			return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.fewParameters),
								DefaultErrors.getErrorNumber(DefaultErrors.fewParameters));
		}
	}
	
	/**
	 * Instructs the components (SRC and TWC) to perform a complete <b>ONLINE</b> data switch over from
	 * the source to the destination databases.
	 * This type of data migration should be executed in case one wants also to take advantage of data <b>synchronization<b/>.
	 * This type of data migration is recoverable, i.e., in case of a crash of a component
	 * the data migration can be restarted from the point where it stopped. 
	 * @param source The source database identifier (e.g., DATASTORE, TABLES).
	 * @param destination The destination database identifier (e.g., DATASTORE, TABLES).
	 * @param threads The number of TWT (i.e., the number of parallel threads writing towards the target database).
	 * @param vdpSize The exponent for the base number 10 which, together, define the VDP size (e.g., 2 means that each VDP will contain 100 entities). 
	 * @param srts The number of SRTs (i.e., the number of parallel threads reading from the source database).
	 * @return A Status object stating whether the command has been properly executed.
	 */
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/switchoverPartitioned")
	public Status switchOverPartitioned(@QueryParam("source") String source,
							@QueryParam("destination") final List<String> destination,
							@QueryParam("threads") int threads,
							@QueryParam("vdpSize") int vdpSize,
							@QueryParam("srts") int srts){
		return MigrateOrRecover(PartitionedCommand.MIGRATE, source, destination, threads, vdpSize, srts);
	}
	
	/**
	 * Instructs the components (SRC and TWC) to perform the RECOVERY of a previous complete <b>ONLINE</b> 
	 * data switch over from the source to the destination databases.
	 * The VDP size is automatically retrieved from ZooKeeper.
	 * @param source The source database identifier (e.g., DATASTORE, TABLES).
	 * @param destination The destination database identifier (e.g., DATASTORE, TABLES).
	 * @param threads The number of TWT (i.e., the number of parallel threads writing towards the target database).
	 *  @param srts The number of SRTs (i.e., the number of parallel threads reading from the source database).
	 * @return A Status object stating whether the command has been properly executed.
	 */
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/recover")
	public Status recoverMigration(@QueryParam("source") String source,
							@QueryParam("destination") final List<String> destination,
							@QueryParam("threads") int threads,
							@QueryParam("srts") int srts){
		try {
			TaskQueue taskQueue = new TaskQueue();
			boolean purged = taskQueue.purgeQueue();
			if(purged){
				taskQueue.disconnect();
				//vdpSize is not considered when recovering
				//as it is automatically retrieved from ZooKeeper
				return MigrateOrRecover(PartitionedCommand.RECOVER, source, destination, threads, 0, srts);
			} else {
				throw new IOException(DefaultErrors.unpurgeableQueue);
			}
	 
		} catch (QueueException e) {
			return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.queueError),
					DefaultErrors.getErrorNumber(DefaultErrors.queueError));
		} catch (ShutdownSignalException | ConsumerCancelledException
				| IOException | InterruptedException e) {
			return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.unpurgeableQueue),
					DefaultErrors.getErrorNumber(DefaultErrors.unpurgeableQueue));
		}
		
		
	}

	/**
	 * Starts a new partitioned data migration from scratch OR recovers the previous one.
	 * @param cmd MIGRATE or RECOVER
	 * @param source The source database identifier (e.g., DATASTORE, TABLES).
	 * @param destination The destination database identifier (e.g., DATASTORE, TABLES).
	 * @param threads The number of TWT (i.e., the number of parallel threads writing towards the target database).
	 * @param vdpSize When migrating from scratch represents the exponent for the base number 10 which, together, define the VDP size (e.g., 2 means that each VDP will contain 100 entities).
	 * @param srts The number of SRTs (i.e., the number of parallel threads reading from the source database). 
	 * @return A Status object stating whether the command has been properly executed.
	 */
	private Status MigrateOrRecover(PartitionedCommand cmd, String source, 
			final List<String> destination, int threads, int vdpSize, int srts){

		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		//BasicConfigurator.configure();
		//Check input params number
		if(source != null && destination != null && destination.size()>=1){
			//check input content
			boolean source_supported = Constants.isSupported(source);
			List<String> supported_dest = Constants.getSupportedDBfromList(destination);
			if(source_supported && supported_dest.size() >= 1){
				String message = "Mapping from "+source+ " to ";
				StringBuilder sb = new StringBuilder(message);
				for(String sup : supported_dest){
					sb.append(sup+" ");
				}
				
				ZKserver zKserver = new ZKserver(PropertiesManager.getZkProperty("connectString"));
				try {
					if(cmd.equals(PartitionedCommand.MIGRATE)){
						if(vdpSize<1 || vdpSize>5)
							return new Status(Constants.STATUS_ERROR, 
									"Provide a reasonable exponent p so that 10^p represents"
									+ " a resonable VDP size (i.e., 1 < p < 6)",
									"UNKOWN");
						zKserver.setVDPsize(vdpSize);
					}else if(cmd.equals(PartitionedCommand.RECOVER)){
						int vdPsize = zKserver.getVDPsize();
						if(vdPsize<1 || vdPsize>5)
							return new Status(Constants.STATUS_ERROR, 
									"Retrieved VDP size is not correct: "
									+ vdPsize,
									"UNKOWN");
					}
					
				} catch (Exception e1) {
					if(cmd.equals(PartitionedCommand.MIGRATE)){
						return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.vdpError),
								DefaultErrors.getErrorNumber(DefaultErrors.vdpError));
					} else if(cmd.equals(PartitionedCommand.RECOVER)){
						return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.vdpRetrievalError),
								DefaultErrors.getErrorNumber(DefaultErrors.vdpRetrievalError));
					}
					
				}
				
				
				try {
					
					if(queue.checkPresence()){
						log.info("Components present");
						ServiceQueueMessage sqm = new ServiceQueueMessage();
						if(cmd.equals(PartitionedCommand.MIGRATE)){
							sqm.setCommand("switchoverPartitioned");
						} else if(cmd.equals(PartitionedCommand.RECOVER)){
							sqm.setCommand("recover");
						}
						
						sqm.setSource(source);
						sqm.setDestination(destination);
						sqm.setThreads(threads);
						sqm.setSRTs_NO(srts);
						
						byte[] ssqm = DefaultSerializer.serialize(sqm);
						queue.publish("SRC", ssqm);
						queue.publish("TWC", ssqm);
					}
				} catch (QueueException e) {
					
					return new Status(Constants.STATUS_ERROR, 
							DefaultErrors.getErrorNumber(DefaultErrors.queueError),
							DefaultErrors.getErrorMessage(DefaultErrors.queueError));
				} catch (IOException e) {
					e.printStackTrace();
					return new Status(Constants.STATUS_ERROR, e.getMessage());
				}
				
				
				return new Status(Constants.STATUS_SUCCESS, sb.toString());
			} else {
				//Cannot switchover - Not supported databases
				return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.databaseNotSupported),
									DefaultErrors.getErrorNumber(DefaultErrors.databaseNotSupported));
			}
		} else{
			//Cannot switchover - Few parameters
			return new Status(Constants.STATUS_ERROR, DefaultErrors.getErrorMessage(DefaultErrors.fewParameters),
								DefaultErrors.getErrorNumber(DefaultErrors.fewParameters));
		}
	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	/**
	 * Launched during application boot (see {@link src/main/webapp/WEB-INF/web.xml}).
	 * It instantiates the Queue object which, on construction, binds the Exchange to 
	 * the queue "Q1"
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized method called");
		try {
			queue = new Queue();
		} catch (QueueException e) {
			e.printStackTrace();
		}
	}
	
	private enum PartitionedCommand{MIGRATE, RECOVER}
}
