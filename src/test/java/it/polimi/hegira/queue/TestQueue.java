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
