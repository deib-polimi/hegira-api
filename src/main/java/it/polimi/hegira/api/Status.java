package it.polimi.hegira.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
	private String status;
	private String message;
	private String error_code;
	public Status(String status, String message, String error_code) {
		this.status = status;
		this.message = message;
		this.error_code = error_code;
	}
	public Status(String status) {
		this.status = status;
	}
	public Status(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public Status() {
	}
	
	/**
	 * The status given in response to a REST request.
	 * Allowed statuses are: OK, WARNING, ERROR.
	 * @return OK, WARNING or ERROR.
	 */
	@org.codehaus.enunciate.ClientName(value = "State")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Message associated to the Status response. 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Error code associated with an ERROR Status response. 
	 * @return
	 */
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	
}
