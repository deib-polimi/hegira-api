/**
 * 
 */
package it.polimi.hegira.api;

import java.util.List;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.queue.Queue;
import it.polimi.hegira.utils.Constants;
import it.polimi.hegira.utils.DefaultErrors;

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
 * @author Marco Scavuzzo
 *
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
					}
				} catch (QueueException e) {
					
					return new Status(Constants.STATUS_ERROR, 
							DefaultErrors.getErrorNumber(DefaultErrors.queueError),
							DefaultErrors.getErrorMessage(DefaultErrors.queueError));
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
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized method called");
		try {
			queue = new Queue();
		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
