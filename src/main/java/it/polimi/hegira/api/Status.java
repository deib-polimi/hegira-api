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
