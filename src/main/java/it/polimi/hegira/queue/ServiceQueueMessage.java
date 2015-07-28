/**
 * 
 */
package it.polimi.hegira.queue;

import it.polimi.hegira.utils.Constants;

import java.io.Serializable;
import java.util.List;

/**
 * Class defining the structure of a command message passed in the service queue.
 * (To be serialized with the standard Java serialization {@link it.polimi.hegira.utils.DefaultSerializer})
 * @author Marco Scavuzzo
 */
public class ServiceQueueMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String source;
	private List<String> destination;
	private int threads;
	private String command; 
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		if(Constants.isSupported(source))
			this.source = source;
	}
	public List<String> getDestination() {
		return destination;
	}
	public void setDestination(List<String> destination) {
		List<String> supported_dest = Constants.getSupportedDBfromList(destination);
		this.destination = supported_dest;
	}
	public int getThreads() {
		return threads;
	}
	public void setThreads(int threads) {
		this.threads = threads;
	}
}