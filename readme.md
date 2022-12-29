# Kafka Projects

This repository contains some of my kafka study projects

## Kafka commands:

### start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

### start kafka
bin/kafka-server-start.sh config/server.properties

### list all kafka topics
./bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list

### describe all kafka topics
./bin/kafka-topics.sh --bootstrap-server=localhost:9092 --describe

### create a new topic
bin/kafka-topcis.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic TOPIC_NAME

### create a new kafka producer
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic TOPIC_NAME

### create a new kafka consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TOPIC_NAME --from-beginning

### alter the partitions number of a topic
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic TOPIC_NAME --partitions NUMBER_OF_PARTITIONS
OBS.: The number of partitions must be greater or equal then the numbers of consumers of a group

### describe all kafka groups
./bin/kafka-consumer-groups.sh --all-groups --bootstrap-server localhost:9092 --describe

### delete a kafka topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic TOPIC_NAME