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
package it.polimi.hegira.queue;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.utils.PropertiesManager;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.Queue.PurgeOk;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * TaskQueue class used exclusively to purge the task queue.
 *  
 * @author Marco Scavuzzo
 */
public class TaskQueue {
	private static Logger log = Logger.getLogger(TaskQueue.class);

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private QueueingConsumer consumer;
	protected static final String TASK_QUEUE_NAME = "task_queue";

	/**
	 * Creates a task queue between the SRC and the TWC.
	 * @param queueAddress The address where the RabbitMQ broker is deployed. Default: localhost
	 * @throws QueueException If a connection cannot be established.
	 */
	public TaskQueue() throws QueueException{
		
		factory = new ConnectionFactory();
		String queueProperty = PropertiesManager.getQueueProperty("host");
		if(queueProperty==null || queueProperty.isEmpty()){
			throw new QueueException();
		}
		factory.setHost(queueProperty);
		
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			/**
			 * Declaring a durable queue
			 * queueDeclare(java.lang.String queue, boolean durable, 
			 * boolean exclusive, boolean autoDelete, 
			 * java.util.Map<java.lang.String,java.lang.Object> arguments) 
			 */
			channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
			channel.basicQos(1);
			consumer = new QueueingConsumer(channel);
			/**
			 * basicConsume(java.lang.String queue, boolean autoAck, Consumer callback)
			 * Starts a non-nolocal, non-exclusive consumer, 
			 * with a server-generated consumerTag.
			 */
			channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
					
			
		} catch (IOException e) {
			log.error(e.toString(), e);
		}
	}
	
	/**
	 * Acknowledge a message given its delivery (returned from the QueuingConsumer object).
	 * @param delivery
	 * @throws QueueException
	 */
	public void sendAck(Delivery delivery) throws QueueException{
		try {
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		} catch (IOException e) {
			throw new QueueException(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * In case a message cannot be processed properly a negative acknowledgment must be sent.
	 * @param delivery The message that was not processed.
	 * @throws QueueException
	 */
	public void sendNack(Delivery delivery) throws QueueException{
		try {
			/**
			 * void basicNack(long deliveryTag,
             *					boolean multiple,
             *					boolean requeue)
			 */
			channel.basicNack(delivery.getEnvelope().getDeliveryTag(),
					false, true);
			/**
			 * void basicReject(long deliveryTag,
             *					boolean requeue)
			 */
			channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
		} catch (IOException e) {
			throw new QueueException(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * Purges the task queue.
	 * @return <b>true</b> if purged; <b>false</b> otherwise.
	 * @throws IOException
	 * @throws ShutdownSignalException
	 * @throws ConsumerCancelledException
	 * @throws InterruptedException
	 * @throws QueueException
	 */
	public boolean purgeQueue() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, QueueException{
		PurgeOk queuePurge = channel.queuePurge(TASK_QUEUE_NAME);
		if(queuePurge!=null){
			Delivery delivery = consumer.nextDelivery(50);
			if(delivery!=null){
				sendAck(delivery);
				log.debug(Thread.currentThread().getName()+
						" consumed orphan");
			}
		}
		return (queuePurge != null) ?  true :  false;
	}
	
	/**
	 * Disconnects the client from the task queue closing the channel and the connection.
	 * @throws IOException
	 */
	public void disconnect() throws IOException{
		if(channel!=null) channel.close();
		if(connection!=null) connection.close();
	}
}
