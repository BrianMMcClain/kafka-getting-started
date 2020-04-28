package com.github.brianmmcclain.kafkagettingstarted;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class consumer {

	private final static String TOPIC_NAME = "hello";

	public static void main(String[] args) {

		// Set the properties to use when connecting to Kafka
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:29092");
		props.put("group.id", "kafka-getting-started");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
		// Subscribe to the topic in Kafka
		consumer.subscribe(Arrays.asList(TOPIC_NAME));

		// Begin polling Kafka for new messages
		System.out.println("Waiting for messages. To exit press CTRL+C\n");
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records)
				System.out.println(record.value());
		}
	}
}
