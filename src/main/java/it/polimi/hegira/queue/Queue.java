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
			channelPublish = connection.createChannel();
			channelPublish.exchangeDeclare(EXCHANGE_NAME, "direct");
			
			//RECEIVER PART
			channelConsume = connection.createChannel();
			channelConsume.exchangeDeclare(EXCHANGE_NAME, "direct");
			String queueName = channelConsume.queueDeclare().getQueue();
			//routing key bindings
			channelConsume.queueBind(queueName, EXCHANGE_NAME, "toApiServer");
			consumer = new QueueingConsumer(channelConsume);
			/**
			 * basicConsume(java.lang.String queue, boolean autoAck, Consumer callback)
			 * Start a non-nolocal, non-exclusive consumer, 
			 * with a server-generated consumerTag.
			 */
			channelConsume.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			log.error(e.toString());
			
		}
	}
	
	public void publish(String routingKey, byte[] messageBody) throws QueueException{
		/**
		 * basicPublish(java.lang.String exchange, 
		 * 		java.lang.String routingKey, 
		 * 		AMQP.BasicProperties props, byte[] body)
		 */
		try {
			channelPublish.basicPublish(EXCHANGE_NAME, routingKey, null, messageBody);
		} catch (IOException e) {
			throw new QueueException();
		}
	}
	
	public boolean checkPresence(){
		int i=0;
		while(true){
			QueueingConsumer.Delivery delivery;
			try {
				delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
            		String routingKey = delivery.getEnvelope().getRoutingKey();
            		if(message.equals("SRC") || message.equals("TWC")){
            			i++;
            		}
			} catch (ShutdownSignalException | ConsumerCancelledException
					| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
