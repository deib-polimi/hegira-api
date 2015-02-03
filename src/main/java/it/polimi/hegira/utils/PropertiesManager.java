/**
 * 
 */
package it.polimi.hegira.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Marco Scavuzzo
 *
 */
public class PropertiesManager {
	private static Logger log = Logger.getLogger(PropertiesManager.class);

	
	private static String getPropertyFromFile(String file, String propertyKey){
		
		Properties props = new Properties();
		URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
		
		try {
			InputStream isr = new FileInputStream(resource.getFile());
			props.load(isr);
			return props.getProperty(propertyKey);
		} catch (FileNotFoundException | NullPointerException e) {
			log.error(file+" file must exist!");
		} catch (IOException e) {
			log.error("Unable to read file "+file+"!");
		} finally {
			props=null;
		}
		
		return null;
	}
	
	public static String getQueueProperty(String propertyKey){
		return getPropertyFromFile(Constants.QUEUE_PATH, propertyKey);
	}
	
	public static String getZkProperty(String propertyKey){
		return getPropertyFromFile(Constants.ZK_PATH, propertyKey);
	}
}
