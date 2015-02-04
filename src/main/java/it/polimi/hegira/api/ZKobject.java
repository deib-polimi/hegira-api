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
	public String getLowExtreme() {
		return lowExtreme;
	}
	public void setLowExtreme(String lowExtreme) {
		this.lowExtreme = lowExtreme;
	}
	public String getHighExtreme() {
		return highExtreme;
	}
	public void setHighExtreme(String highExtreme) {
		this.highExtreme = highExtreme;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
