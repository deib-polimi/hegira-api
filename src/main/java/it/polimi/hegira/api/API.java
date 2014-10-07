/**
 * 
 */
package it.polimi.hegira.api;

import java.io.IOException;
import java.util.List;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.queue.Queue;
import it.polimi.hegira.queue.ServiceQueueMessage;
import it.polimi.hegira.utils.Constants;
import it.polimi.hegira.utils.DefaultErrors;
import it.polimi.hegira.utils.DefaultSerializer;

import javax.servlet.ServletContextEvent;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class providing the REST API for Hegira.
 * @author Marco Scavuzzo
 */
@Path("/api")
public class API implements javax.servlet.ServletContextListener {
	private transient Logger log = Logger.getLogger(API.class);
	private static Queue queue;
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String sayPlainTextHello() {
		return "Hello from Hegira!";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(){
		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		
		return Constants.getAPIpage();
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/publish")
	public Status publish(){
		try {
			Queue queue = new Queue();
			queue.publish("toComponents", "Ciao".getBytes());
			return new Status(Constants.STATUS_SUCCESS);
		} catch (QueueException e) {
			return new Status("ERROR",DefaultErrors.getErrorMessage(DefaultErrors.queueError), DefaultErrors.getErrorNumber(DefaultErrors.queueError));
		}
	}
	
	
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
				
				/**
				 * TODO: Send migration message to the service-queue.
				 */
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
}
