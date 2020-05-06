# kafka-getting-started

An example of how to connect to, send, and receive messages from Kafka in several languages.

## Start Kafka

Kafka can be started in Docker using the included `docker-compose.yml` file using Docker Compose

`docker-compose up`

## Running The Demo

For the Java example, you can open up two terminals, one opened up to `kafka-getting-started/java/emitter` and the other to `kafka-getting-started/java/consumer`. In both terminals, start the Spring application with

`./mvnw spring-boot:run`

On the emitter, you'll be prompted for input. You can begin typing a message, and send it with [Enter]. You should then see the same message written in the terminal running the consumer.
