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

import it.polimi.hegira.utils.Constants;
import it.polimi.hegira.utils.PropertiesManager;
import it.polimi.hegira.zkWrapper.ZKclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/**
 * Customized ZooKeeper web service
 * to be used by PaaS applications to:
 * 	1. request new unique ids
 * 	2. check the synchronization status
 * @author Marco Scavuzzo
 */
@Path("/zkService")
public class ZKservice {
	private transient Logger log = Logger.getLogger(ZKservice.class);
	
	/**
	 * Returns a range of maximum 100 (minimum 2) unique ids to the caller for a given table.
	 * @param tableName The name of the table.
	 * @param offset the number of ids to be assigned (less than 101)
	 * @return The first and the last ids in the assigned range.
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/getRange")
	public ZKobject assignSeqNrRange(@QueryParam("tableName") String tableName, 
			@QueryParam("offset") int offset){
		ZKclient zKclient=null;
		ZKobject zKobject = null;
		try{
			zKclient = new ZKclient(PropertiesManager.getZkProperty("connectString"));
			int[] assignSeqNrRange = zKclient.assignSeqNrRange(tableName, offset);
			zKobject = new ZKobject(assignSeqNrRange[0], assignSeqNrRange[1]);
			zKobject.setRequestStatus(new Status(Constants.STATUS_SUCCESS));
		} catch (Exception e) {
			zKobject = new ZKobject();
			zKobject.setRequestStatus(new Status(Constants.STATUS_ERROR,
					e.getMessage()));
			log.error("Request "+Constants.STATUS_ERROR, e);
		}finally{
			if(zKclient!=null){	
				zKclient.close();
				zKclient=null;
			}
		}
		return zKobject;
	}
	
	/**
	 * Returns a unique id to the caller for a given table.
	 * @param tableName The name of the table.
	 * @return The unique id
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/getId")
	public ZKobject assignSeqNr(@QueryParam("tableName") String tableName){
		ZKclient zKclient=null;
		ZKobject zKobject = null;
		try{
			zKclient = new ZKclient(PropertiesManager.getZkProperty("connectString"));
			int assignSeqNr = zKclient.assignSeqNr(tableName);
			zKobject = new ZKobject(assignSeqNr);
			zKobject.setRequestStatus(new Status(Constants.STATUS_SUCCESS));
		} catch (Exception e) {
			zKobject = new ZKobject();
			zKobject.setRequestStatus(new Status(Constants.STATUS_ERROR,
					e.getMessage()));
			log.error("Request "+Constants.STATUS_ERROR, e);
		}finally{
			if(zKclient!=null){	
				zKclient.close();
				zKclient=null;
			}
		}
		
		return zKobject;
	}
	
	/**
	 * Returns the status of Hegira, i.e., if it has been configured to synchronize data.
	 * @return <b>true</b> if it is synchronizing, <b>false</b> otherwise
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/isSynchronizing")
	public ZKobject isSynchronizing(){
		ZKclient zKclient=null;
		ZKobject zKobject = null;
		try{
			zKclient = new ZKclient(PropertiesManager.getZkProperty("connectString"));
			boolean isSync = zKclient.isSynchronizing(null);
			zKobject = new ZKobject(isSync);
			zKobject.setRequestStatus(new Status(Constants.STATUS_SUCCESS));
		} catch (Exception e) {
			zKobject = new ZKobject();
			zKobject.setRequestStatus(new Status(Constants.STATUS_ERROR,
					e.getMessage()));
			log.error("Request "+Constants.STATUS_ERROR, e);
		}finally{
			if(zKclient!=null){	
				zKclient.close();
				zKclient=null;
			}
		}
		return zKobject;
	}
}
