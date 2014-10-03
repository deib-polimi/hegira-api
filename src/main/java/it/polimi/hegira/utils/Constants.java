package it.polimi.hegira.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Constants {
	
	/**
	* LOGS FILE PATH
	*/
	public static final String LOGS_PATH = "log.properties";
	
	/**
	 * QUEUE FILE PATH
	 */
	public static final String QUEUE_PATH = "queue.properties";
	
	/**
	* STATUS RESPONSE
	*/
	public static final String STATUS_SUCCESS = "OK";
	public static final String STATUS_ERROR = "ERROR";
	public static final String STATUS_WARNING = "WARNING";
	
	/**
	* SUPPORTED DATABASE IDENTIFIERS
	*/
	public static final String GAE_DATASTORE="DATASTORE";
	public static final String AZURE_TABLES="TABLES";
	public static final String AMAZON_DYNAMODB="DYNAMODB";
	public static List<String> getSupportedDatabseList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(GAE_DATASTORE);
		list.add(AZURE_TABLES);
		list.add(AMAZON_DYNAMODB);
		return list;
	}
	public static boolean isSupported(String database){
		List<String> supportedList = getSupportedDatabseList();
		return supportedList.contains(database);
	}
	public static List<String> getSupportedDBfromList(List<String> databases){
		List<String> supportedList = getSupportedDatabseList();
		databases.retainAll(supportedList);
		return databases;
	}
}
