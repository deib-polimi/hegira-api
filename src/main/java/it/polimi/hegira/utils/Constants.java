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
	 * ZOOKEEPER FILE PATH
	 */
	public static final String ZK_PATH = "zookeeper.properties";
	
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
	
	/**
	* HTML
	*/
	private static final String API_intro = "<html>\n\t<head>\n\t\t<title>Modaclouds_DB | API</title>\n\t\t<meta content=\"Interoperable data migration between NoSQL columnar databases. API intro.\" name=\"description\" />\n\t\t<meta content=\"NoSQL,Google AppEngine Datastore,Azure Tables,DynamoDB, Data mapping\" name=\"keywords\" />\n\t\t<meta content=\"Marco Scavuzzo\" name=\"author\" />\n\t\t<meta charset=\"UTF-8\" />\n\t</head>\n\t<body>\n\t\t<h1>\n\t\t\t<img alt=\"Logo\" src=\"http://modaclouds.eu/img/image4094_1.png\" style=\"width: 111px; height: 65px; float: left; margin: 2px; border-width: 0px; border-style: solid;\" />Modaclouds_DB - Interoperable data migration between NoSQL columnar databases</h1>\n\t\t<br />\n\t\t<p>\n\t\t\t<strong>API calls</strong></p>\n\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 700px;\">\n\t\t\t<thead>\n\t\t\t\t<tr>\n\t\t\t\t\t<th scope=\"col\">\n\t\t\t\t\t\tName</th>\n\t\t\t\t\t<th scope=\"col\">\n\t\t\t\t\t\tDescription</th>\n\t\t\t\t\t<th scope=\"col\">\n\t\t\t\t\t\tHTTP Method</th>\n\t\t\t\t\t<th scope=\"col\">\n\t\t\t\t\t\tResult</th>\n\t\t\t\t\t<th scope=\"col\">\n\t\t\t\t\t\tRequest</th>\n\t\t\t\t</tr>\n\t\t\t</thead>\n\t\t\t<tbody>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tSwitch over</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tMaps a database into others</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tPOST</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStatus</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/switchover?source=DATASTORE&amp;destination=TABLES&amp;destination=...&amp;threads=32</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStandardize datastore</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tUniforms datastore entities</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tGET</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStatus</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/standardizeDatastore?kind=UserRatings&amp;kind=Topics&amp;...</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStore credentials</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tCreates a new credential into the system</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tPUT</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStatus</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/credentials?name=datastore.username&amp;value=polimi.modaclouds@gmail.com</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tGet credential</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tGets the value for a given credential name</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tGET</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tProperty</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/credentials?name=datastore.username</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tUpdate credential</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tUpdates the value for a given credential name</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tPOST</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStatus</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/credentials?name=datastore.username&amp;value=polimi.modaclouds@gmail.com</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tDelete credential</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tDeletes a credential from the system</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tDELETE</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tStatus</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tapi/credentials?name=datastore.username</td>\n\t\t\t\t</tr>\n\t\t\t</tbody>\n\t\t</table>\n\t\t<p style=\"text-align: center;\">\n\t\t\t&nbsp;</p>\n\t\t<p style=\"text-align: center;\">\n\t\t\t<strong>Marco Scavuzzo</strong></p>\n\t\t<p style=\"text-align: center;\">\n\t\t\tCopyright 2014</p>\n\t</body>\n</html>";
	public static String getAPIpage(){
		return API_intro;
	}
}