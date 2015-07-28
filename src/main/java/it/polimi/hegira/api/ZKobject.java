/**
 * 
 */
package it.polimi.hegira.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marco Scavuzzo
 *
 */
@XmlRootElement
public class ZKobject {
	
	private String lowExtreme;
	private String highExtreme;
	private String id;
	private Status requestStatus;
	private String isSynch;
	
	public ZKobject(int lowExtreme, int highExtreme) {
		this.lowExtreme = ""+lowExtreme;
		this.highExtreme = ""+highExtreme;
	}
	public ZKobject(int id) {
		this.id = ""+id;
	}
	public ZKobject(boolean isSynch) {
		this.isSynch = ""+isSynch;
	}
	public ZKobject(){}
	
	/**
	 * The lowest extreme of the generated range of unique ids.
	 * @return The lower extreme of the generated range of unique ids.
	 */
	public String getLowExtreme() {
		return lowExtreme;
	}
	public void setLowExtreme(String lowExtreme) {
		this.lowExtreme = lowExtreme;
	}
	
	/**
	 * The highest extreme of the generated range of unique ids.
	 * @return The highest extreme of the generated range of unique ids.
	 */
	public String getHighExtreme() {
		return highExtreme;
	}
	public void setHighExtreme(String highExtreme) {
		this.highExtreme = highExtreme;
	}
	/**
	 * Returns the generated unique id.
	 * @return The generated unique id.
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * The status given in response to the REST request.
	 * @return The status given in response to the REST request.
	 */
	public Status getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String isSynch() {
		return isSynch;
	}
	public void setSynch(String isSynch) {
		this.isSynch = isSynch;
	}
}
