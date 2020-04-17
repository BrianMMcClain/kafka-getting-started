package com.github.brianmmcclain.rabbitmqgettingstarted;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class consumer {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {
		
		// Connect to the RabbitMQ server
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		try {
			Connection connection = factory.newConnection();

			// Create a channel and connect it to the queue
			Channel channel = connection.createChannel();
			System.out.println("Waiting for messages. To exit press CTRL+C\n");

			// Setup the callback to be invoked when a new message is received
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				// Read the message from the message object and print to stdout
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" > " + message);
			};

			// Begin consuming messages from the queue. The second argument will tell
			// RabbitMQ to automatically consider the message acknowledged once received.
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
