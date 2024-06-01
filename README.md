# Apache Kafka
Apache Kafka is an open source distributed event/message streaming platform. Kafka is used for the realtime event streaming. Real time event streaming is nothing but continuously sending the stream of events to the system. Kafka server supports distributed systems, meaning that we can create multiple instances of kafka server which serves different regions. If one server is down, another server will come up to process the events.
- Kafka was originally developed by Linked-in and was subsequently open sourced in 2011.
- Where does we use Kafka? - Let us say there are two systems which talks to each other. When System1 sends the message to System2 and the System2 is not available to receive the message then there is a need for one intermediatory system to hold the message till the System2 comes up and receives the message. Kafka serves the purpose of this intermediate system. There are other systems like RabitMQ and Redis also serves the same purpose.
- Kafka works in Publisher and Subscriber model. 
- Components in Kafka
	- **Producer-** Producer is the source of data who publishes messages or evenets. Producer is an application produces messages and sends to the server. Producer doesn't sent messages directly to the consumer.
	- **Consumer-** Consumer is the receiver and acts as a consumer/receiver for receiving the messages. Consumer an ask for messges sent from any producer.  
	- **Broker-** Producer and consumer will not talk to eacg other and there is a middle man called kafka server/ broker. Producer posts the messages to broker/server. Consumer receives/ consumes messages from broker. Kafka server acts as broker between producer and consumer.
	- **Cluster-** Cluster is nothing but a group of systems together. There will be one or more Kafka servers in a kafka cluster. Generally a cluster has a minimum of 3 brokers.
	- **Topic-** Producer sends N no of messages and consumer consumes the same. We need to categorize the type of messages coming in and going out from the server. Topic is the one who serves this purpose. All relevent messages would be added to a specific topic. Topic specifies the category or classification of the message. Listeners can then just respond to messages that belong to the topics they are listening on. Each topic will have its own identifier.
	- **Partitions-** If there is huge volume of data, it is difficult to handle it in topics. So we further devide the topic into sub partitions called topic partitioning. We can decide the no of partitions for each topic during kafka topic partitioning.
	- **Offset-** Whenever the producer sends the message, the message will be sitting in a topic partition. For each message, a number will be assigned and that is called offset. The purpose of offset is to keep track of messages which are already consumed by the consumer. Offset starts with index 0 and it continues.
	- **Consumer Groups-** So one consumer will be consuming all the messages from multiple partitions and which is a overhead for consumer. So instead of one, we can have N no of consumers and they can work together to process the messages which is called a Consumer group. If all consumers are occupied and processing the events, then for the subsequest events will be on hold till the consumer is available. Once the consumer is available, it will be assigned the events from the queue. This is called consumer rebalancing. 
	- **Zookeeper-** It is a key component of Kafka. It acts as a manager for our complete kafka cluster.
	![Apache Kafka](https://github.com/anand-tummapudi/Kafka/blob/main/assets/kafka.JPG)
## How to download and run Kafka on windows machine.
- Download apache kafka [Download Kafka](https://kafka.apache.org/downloads)
- Follow Kafka wuick start guide [Follow Kafka quick start guide](https://kafka.apache.org/quickstart)
- Run Kafka zookeeper and kafka server from the bin folder. Make sure you are running the exe files from windows folder when you are running Kafka on windowsmachine.
```
C:\kafka_2.13-3.7.0\bin\windows>zookeeper-server-start.bat "C:\kafka_2.13-3.7.0\config\zookeeper.properties"
C:\kafka_2.13-3.7.0\bin\windows>kafka-server-start.bat "C:\kafka_2.13-3.7.0\config\server.properties"
```
 - **zookeeper.proerties** file contains configuration settings for zookeeper, which is a coordination service used by Kafka to manage and co ordinate its distributed components.
   -- dataDir :- directory where zookeeper stores its data.
   -- clientPort :- port number, the clients uses to coneect to zookeeper.
   -- tickTime :- length of single tick in millin secods used to determine the length of zookeeper session.
   -- initLimit :- The no of ticks that a follower zookeeper node will wait before connecting to a leader.
   -- syncLimit :- The no of ticks that a follower zookeeper node will wait before syncing with a leader.
   -- server.1,server.2 :- These settings specify the host names or ip addresses of the zookeeper servers.
   -- weight - The weight of the each zookeeper server.
   -- leaderServes :- Whether the leader zookeeper node will also act as a follower.
 
- Create Kafka topic using the following command.
```
$ kafka-topics.bat --create --topic "sample-topic" --bootstrap-server localhost:9092
```
- Creating a producer to produce the events. After creating the producer, it will give the shell prompt where ou cn create N number of events or messages you want to create. Once you create, can exit from the shell by hitting ctrl+c.
```
$ kafka-console-producer.bat --topic sample-topic --bootstrap-server localhost:9092
```
- Creating the consumer console to consume the messages created from the producer above. Here we need to mention the attribute "--from-beginning" to the consumer command to specify the position to read the messages from. 
```
$ kafka-console-consumer.bat --topic sample-topic --from-beginning --bootstrap-server localhost:9092
```
## How to use Kafka broker in Spring Boot Applications
Spring boot provides an efficient packages to integrate kafka. Without springboot, we need to write a vast code to register and call kafka borker server, producer and consumers. 
- To make use of kafka in spring boot application, we need to add kafka dependencies for spring boot application. Without spring boot, we neeed to write all boiler plate code to configure producer, consumer, kafka template and all other properties required.
- Checkout spring boot apache kafka documentation [Springboot Kafka](https://docs.spring.io/spring-kafka/reference/html/) 
- Configure producer and consumer properties in application.properties file. 
	* spring.kafka.consumer.bootstrap-servers: Configure all apache kafka brokers. Can configure multiple brokers in cluster.
	* spring.kafka.consumer.group-id: Mention the consumer group, where our consumer belongs to.
	* spring.kafka.consumer.group-id: This property specifies what to do when there is no initial offset in kafka or if the current offset does not exist on the server.
	* spring.kafka.consumer.key-deserializer: It is used to deserialize the key in a message
	* spring.kafka.consumer.value-deserializer: It is used to deserialize the value in a message
	* spring.kafka.producer.bootstrap-servers: Kafka broker services.
	* Also we can use similar deserializer classes like consumer for producer messages key and value.
```
spring.kafka.consumer.bootstrap-servers: localhost:9092, localhost:9091, localhost:9093
spring.kafka.consumer.group-id: consumer-group
spring.kafka.consumer.auto-offset-reset:earliest
spring.kafka.consumer.key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer: org.apache.kafka.common.serialization.StringDeserializer

spring.kafka.producer.bootstrap-servers: localhost:9092
spring.kafka.producer.key-serializer: org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer: org.apache.kafka.common.serialization.StringSerializer
```
- Creating a Topic in a spring boot application. Create a configuration class and create a method of type NewTopic to create the topic from the TopiCBuilder and return.
- Create a Producer class - Crete a service class and add KafkaTemplate to it. If we are not using spring boot we need to create Kafka template manually. KafkaTemplate is a thin wrapper around a Kafka producer that plays nicely with other Spring features like dependency injection and automatic configuration. It provides a number of convenience methods for producing to Kafka topics. create a method inside the service class to send messages to kafka topic. 
- Creating a rest API to send the messages to Kafka producer. Inject the producer created and send the message from rest service.
- Once we hit the rest endpoint to send the message to producer and the request is success, we can use the following kafka command to view the message from kafka console.
 ```
 $ kafka-console-consumer.bat --topic first-topic --from-beginning --bootstrap-server localhost:9092
 ```
 - Create a Kafka consumer lass to consume the messages/events. Create one service class and create a subscriber method to consume messages. We use KafkaLister to subscribe to topic.