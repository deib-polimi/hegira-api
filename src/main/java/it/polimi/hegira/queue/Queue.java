package it.polimi.hegira.queue;

import it.polimi.hegira.exceptions.QueueException;
import it.polimi.hegira.utils.PropertiesManager;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Queue {
	private static Logger log = Logger.getLogger(Queue.class);

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channelPublish;
	private Channel channelConsume;
	private static final String EXCHANGE_NAME = "service-queue";
	private static final String LISTEN_RK = "toApiServer";
	QueueingConsumer consumer;

	public Queue() throws QueueException{
		factory = new ConnectionFactory();
		String queueProperty = PropertiesManager.getQueueProperty("host");
		if(queueProperty==null || queueProperty.isEmpty()){
			throw new QueueException();
		}
		factory.setHost(queueProperty);
		try {
			connection = factory.newConnection();
			//SENDER PART
			channelPublish = connection.createChannel();
			/**
			 * direct exchange: a message goes to the queues whose binding key 
			 * exactly matches the routing key of the message.
			 */
			channelPublish.exchangeDeclare(EXCHANGE_NAME, "direct");
			log.debug("Publish channel created. Exchange: "+EXCHANGE_NAME+" type: direct");
			
			//RECEIVER PART
			channelConsume = connection.createChannel();
			channelConsume.exchangeDeclare(EXCHANGE_NAME, "direct");
			log.debug("Consuming channel created. Exchange: "+EXCHANGE_NAME+" type: direct");
			
			//String queueName = channelConsume.queueDeclare().getQueue();
			/**
			 * queueDeclare(java.lang.String queue, boolean durable, 
			 * boolean exclusive, boolean autoDelete, 
			 * java.util.Map<java.lang.String,java.lang.Object> arguments) 
			 */
			String queueName = channelConsume.queueDeclare("Q1", false, false, false, null).getQueue();
			
			/**
			 * routing key bindings: relationship between an exchange and a queue.
			 */
			channelConsume.queueBind(queueName, EXCHANGE_NAME, LISTEN_RK);
			log.debug("Binding the consuming channel. ROUTING KEY: "+LISTEN_RK+" QUEUE NAME: "+queueName);
			
			
			/**
			 * Telling the server to deliver us the messages from the queue. 
			 * Since it will push us messages asynchronously, we provide a callback 
			 * in the form of an object (QueueingConsumer) that will buffer the messages 
			 * until we're ready to use them.
			 */
			consumer = new QueueingConsumer(channelConsume);
			/**
			 * basicConsume(java.lang.String queue, boolean autoAck, Consumer callback)
			 * Starts a non-nolocal, non-exclusive consumer, 
			 * with a server-generated consumerTag.
			 */
			channelConsume.basicConsume(queueName, true, consumer);
			log.debug("Consumer started on queue: "+queueName);
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
	
	/**
	 * Writes a message in the queue with the given routing key.
	 * @param routingKey
	 * @param messageBody
	 * @throws QueueException if an error has occurred.
	 */
	public void publish(String routingKey, byte[] messageBody) throws QueueException{
		/**
		 * Declaring a queue is idempotent - it will only be created if it doesn't exist already. 
		 * basicPublish(java.lang.String exchange, 
		 * 		java.lang.String routingKey, 
		 * 		AMQP.BasicProperties props, byte[] body)
		 */
		try {
			channelPublish.basicPublish(EXCHANGE_NAME, routingKey, null, messageBody);
			log.debug("Message published. ROUTING KEY: "+routingKey);
		} catch (IOException e) {
			throw new QueueException();
		}
	}
	
	/**
	 * Checks that both the SRC and the TWC have been started and will (eventually) respond
	 * to further requests.
	 * @return <b>true</b> if both the SRC and the TWC have been started, <b>false</b> otherwise.
	 * @throws QueueException if an error has occurred.
	 */
	public boolean checkPresence() throws QueueException{
		int i=0;
		while(true){
			/**
			 * QueueingConsumer.nextDelivery() blocks 
			 * until another message has been delivered from the server.
			 */
			QueueingConsumer.Delivery delivery;
			try {
				delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				log.debug("Got message: "+message);
            		String routingKey = delivery.getEnvelope().getRoutingKey();
            		if(message.equals("SRC") || message.equals("TWC")){
            			i++;
            			log.debug("Retrieved message from "+message);
            		}
			} catch (ShutdownSignalException | ConsumerCancelledException
					| InterruptedException e) {
				throw new QueueException();
			}	
			if(i==2)
				return true;
		}
	}
	
	public static void main(String args[]){
		System.out.println(PropertiesManager.getQueueProperty("host"));
	}
	
	public QueueingConsumer getConsumer(){
		return consumer;
	}
}
