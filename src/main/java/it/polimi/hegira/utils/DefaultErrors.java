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
package it.polimi.hegira.utils;

public class DefaultErrors {
	public static String alreadyConnected = "Error 1: A connection is already in place. Disconnect first.";
	public static String connectionError = "Error 2: Unable to connect to the destination.";
	public static String notConnected = "Error 3: No active connection.";
	public static String credentialsStorageError = "Error 4: Unable to save credentials";
	public static String credentialsGetError = "Error 5: Credential not found";
	public static String credentialsDeleteError = "Error 6: Unable to delete credentials";
	public static String databaseNotSupported = "Error 7: One or all of the selected databases are not supported";
	public static String fewParameters = "Error 8: Too few parameters. Check the documentation";
	public static String queueError = "Error 9: Impossible to access the queue.";
	public static String vdpError = "Error 10: Couldn't set VDP size.";
	public static String vdpRetrievalError = "Error 11: Couldn't get VDP size.";
	public static String unpurgeableQueue = "Error 12: Couldn't purge the Task Queue.";
	
	
	public static String getErrorNumber(String error){
		int start = error.lastIndexOf("Error ");
		int col = error.lastIndexOf(": ");
		return error.substring(start+6, col);
	}
	public static String getErrorMessage(String error){
		int col = error.lastIndexOf(": ");
		return error.substring(col+1);
	}
}
