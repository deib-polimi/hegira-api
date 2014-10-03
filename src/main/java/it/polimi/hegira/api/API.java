/**
 * 
 */
package it.polimi.hegira.api;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.queue.Queue;
import it.polimi.hegira.utils.Constants;
import it.polimi.hegira.utils.DefaultErrors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Marco Scavuzzo
 *
 */
@Path("/api")
public class API {
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(){
		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		
		Queue.main(null);
		
		return "<html>" + "<title>" + "Hello Jersey" + "</title>"
		        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/publish")
	public Status publish(){
		try {
			Queue queue = new Queue();
			queue.publish("toComponents", "Ciao".getBytes());
			return new Status("OK");
		} catch (QueueException e) {
			return new Status("ERROR",DefaultErrors.getErrorMessage(DefaultErrors.queueError), DefaultErrors.getErrorNumber(DefaultErrors.queueError));
		}
	}
	
}
