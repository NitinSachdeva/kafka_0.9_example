# kafka_0.9_example

## Apache Kafka 
Kafka is open-source messaging system developed by Apache Software Foundation. This system is writtern in scala and aims to provide high
high throughput and faster messaging system for real-time data feeds. http://kafka.apache.org/

---

### Online Documenation
You can find online documenation for the project [here](http://kafka.apache.org/documentation.html). This README.md contains the basic information about the sample code in repository.

---

### Technologies:
I am using gradle as dependency management tool in the project which can be downloaded from the [link](http://gradle.org/gradle-download/) .
You will have to set GRADLE_HOME system variable and include gradle bin path in environment PATH variable.
This project is compiled and tested with Java 8 version.

---

### Dependencies includes :

Kafka client library 0.9.0.0
Apache common lang3 , 3.4

---

### Apache kafka Installation:
Download Apache kafka source from [here](http://kafka.apache.org/downloads.html)
untar the file using command : 

```sh
tar -xvf kafka-0.9.0.1-src.tgz 
```
----
### Start ZooKeeper Server :
```sh
sh bin/zookeeper-server-start.sh config/zookeeper.properties
```
Above command start the zookeeper server with zookeeper.properties configurations.All process here are running in foreground, you will have to open different terminal for each process or run the process in bakcground.

---

### Start Kafka server Broker:
```sh
sh bin/kafka-server-start.sh config/server.properties
```
---

### Create a new Topic for Testing:
```sh
sh bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic testTopic --partitions 2 --replication-factor 1
```

Above command creates a topic with is used by producer to send the message. Apache messages are stored as log with 
offset of latest consumed messages.Apache Kafka allows to create parition for single topic, which allows logs to scale beyond a size as
each parition can be on different server on different machine. Also partition allows parallis, which multiple consumer consuming from
single topic but different partition.
Above command creates a topic with name testTopic having 2 partition and replication factor as 1.

---

### Verify topic created :
```sh
sh bin/kafka-topics.sh --list --zookeeper localhost:2181
```
Above command give the list of topics created on broker server.

---

### Sample Project :

Download the sample code from the repository and build the project. Command to build the project is: 
(Command should be executed on root directory)
```sh
gradle clean build
```
This command build the project and create a exectuable jar file in build/libs/ folder. Change the directory to libs
```sh
cd build/libs/
```

---

### Prodcuer :
Start the producer :
```sh
java -jar kafka_0.9_example.jar producer
```
Producer is module which send the message to broker server on specific topic and partition in the topic. This producer creates a MesasgeKey
and MesageValue to be processed and send first 100000 records to parition 0 and following messages to evenly distributed to all partitions.This was done to test the parallism of the consumer.

---

### Consumer:
Start the consumer :
```sh
java -jar kafka_0.9_example.jar consumer consumer1
```
Consumer reads from the messgae broker subscribing to the topic and process the message. There can be multiple instance running subscribed 
to single topic, but consumer cannot be more than the partition of topic as doing that will not ensure of order of message consumed.
Apache Kafka ensure the inorder delivery of messages within parition but not topic.
Above consumer, read the message from the broker polling and adding it to log file.
One can start more than one consumer process here.

---

### Modify the parition:
```sh
sh bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic testTopic --partitions 3
```
Increasing the partition automatically detected by Producer/Consumer and they start using newly added partition while they are running.
No need to restart producer/Consumer here.



