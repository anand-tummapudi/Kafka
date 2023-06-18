# Apache Kafka
Apache Kafka is an open source distributed event streaming platform. Kafka is used for the realtime event streaming. Real time event streaming is is nothing but continuously sending the stream of events to the system. Kafka server supports distributed systems meaning that we can create multiple instances of kafka server which serves different regions. If one server is down, another server will come up to process the events.
- Kafka was originally developed by Linked-in and was subsequently open sourced in 2011.
- Where does we use Kafka - Let us say there are two systems which talks to each other. When system1 sends the message to System2, System2 is not available to receive the message. There one intermediatory system is required to hold the message till the system2 comes up and sends the message. Kafka serves the purpose of this intermediate system. There are other systems like RabitMQ and Redis also serves the same purpose.
- Kafka works in Publisher and Subscriber model. 
- Components in Kafka
	- **Producer-** Producer is the source of data who publishes messages or evenets. 
	- **Consumer-** Consumer is the receiver and acts as a consumer/receiver for receiving the messages. 
	- **Broker-** Producer and consumer will not talk to eacg other and there is a middle man called kafka server/ broker. Producer posts the messages to broker/server. Consumer receives/ consumes messages from broker.
	- **Cluster-** Cluster is nothing but a group of systems together. There will be one or more Kafka servers in a kafka cluster.
	- **Topic-** Producer sends N no of messages and consumer consumes the same. We need to categorize the type of messages coming in and going out from the server. Topic is the one who serves this purpose. All relevent messages would be added to a specific topic. Topic specifies the category or classification of the message. Listeners can then just respond to messages that belong to the topics they are listening on.
	- **Partitions-**
	- **Offset-**
	- **Consumer Groups-**
	- **Zookeeper-**