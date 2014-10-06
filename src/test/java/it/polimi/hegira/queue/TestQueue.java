/**
 * 
 */
package it.polimi.hegira.queue;

import org.apache.log4j.PropertyConfigurator;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.utils.Constants;
import junit.framework.TestCase;

/**
 * @author Marco Scavuzzo
 *
 */
public class TestQueue extends TestCase {
	
	/**
	 * Test method for {@link it.polimi.hegira.queue.Queue#publish(java.lang.String, byte[])}.
	 * @throws QueueException 
	 */
	public void testPublish() {
		String logs = Thread.currentThread().getContextClassLoader().getResource(Constants.LOGS_PATH).getFile();
		PropertyConfigurator.configure(logs);
		
		String RK = "toApiServer", MSG="test message";
		try {
			Queue queue = new Queue();
			queue.publish(RK, MSG.getBytes());
			QueueingConsumer consumer = queue.getConsumer();
		
			Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
    			String routingKey = delivery.getEnvelope().getRoutingKey();
    			assertEquals(message, MSG);
    			assertEquals(routingKey, RK);
    				
		} catch (ShutdownSignalException | ConsumerCancelledException
				| InterruptedException e) {
			e.printStackTrace();
			fail("Error retrieving the message from the queue");
		} catch (QueueException e){
			e.printStackTrace();
			fail("Queue error");
		}
	}

}
