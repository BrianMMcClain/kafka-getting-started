package com.github.brianmmcclain.kafkagettingstarted;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class emitter {

	private final static String TOPIC_NAME = "hello";

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:29092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		//Begin reading user input
		System.out.println("Ready to send messages. Type \"exit\" to quit.");
		while (true) {
			System.out.print("> ");
			String message = System.console().readLine();
			
			if (message.equalsIgnoreCase("exit")) {
				producer.close();
				System.exit(0);
			}

			// Send the message to the queue
			producer.send(new ProducerRecord<String, String>(TOPIC_NAME, "key-" + message, message));
			System.out.println("Sent '" + message + "'");
		}
	}
}
