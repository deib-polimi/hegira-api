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
import org.apache.log4j.PropertyConfigurator;

/**
 * @author marco
 *
 */
public class PropertiesManager {
	private static Logger log = Logger.getLogger(PropertiesManager.class);

	
	public static String getQueueProperty(String propertyKey){
		
		Properties props = new Properties();
		URL resource = Thread.currentThread().getContextClassLoader().getResource(Constants.QUEUE_PATH);
		
		try {
			InputStream isr = new FileInputStream(resource.getFile());
			props.load(isr);
			return props.getProperty(propertyKey);
		} catch (FileNotFoundException | NullPointerException e) {
			log.error(Constants.QUEUE_PATH+" file must exist!");
		} catch (IOException e) {
			log.error("Unable to read file "+Constants.QUEUE_PATH+"!");
		} finally {
			props=null;
		}
		
		return null;
	}
}
