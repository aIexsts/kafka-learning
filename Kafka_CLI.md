# Kafka CLI

## Running Kafka

Mac Kafka Installation directory:
/usr/local/etc/kafka

Start Kafka
 ```bash
 docker-compose -f zk-single-kafka-single.yml up -d
 ```

Stop Kafka
 ```bash
docker-compose -f zk-single-kafka-single.yml stop
 ```

Destroy Kafka
 ```bash
docker-compose -f zk-single-kafka-single.yml down 
 ```


## Kafka Commands

### Connecting

Run Kafka CLI Command 
 ```bash
docker exec -it kafka1 /bin/bash
kafka-topics --version
 ```

Connect to Kafka Conduktor Playground
```bash
[command] --command-config conduktor.config --bootstrap-server cluster.playground.cdkt.io:9092
``` 

### Topics

Create topic
```bash
kafka-topics --command-config conduktor.config  --bootstrap-server cluster.playground.cdkt.io:9092 --create --topic my_topic --partitions 5 --replication-factor 2
``` 

List topics
```bash
--list
``` 

Describe topic
```bash
--topic first_topic --describe
``` 

Delete topic
```bash
--topic second_topic --delete
``` 

### Producer

Producing
```bash
kafka-console-producer --producer.config conduktor.config --bootstrap-server cluster.playground.cdkt.io:9092 --topic my_topic acks=all
``` 

Producing to non existent topic -> will fail
```bash
--topic new_topic
``` 

Producing with key
```bash
--topic first_topic --property parse.key=true --property key.separator=:
``` 

Partioner Round Robin (do not use in Production - most inefficient!)
```bash 
--producer-property partitioner.class=org.apache.kafka.clients.producer.RoundRobinPartitioner --topic my_topic 
```

### Consumer

Start Consuming
```bash
kafka-console-consumer --consumer.config conduktor.config --bootstrap-server cluster.playground.cdkt.io:9092 --topic my_topic
``` 

Consuming from beginning 
```bash
--from-beginning
``` 

Display key, values and timestamp in consumer
```bash
--formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --property print.partition=true
``` 

Consuming by group (start multiple)
```bash
--group my-first-application
``` 

### Consumer Groups

List Consumer Groups
```bash
kafka-consumer-groups --command-config conduktor.config  --bootstrap-server cluster.playground.cdkt.io:9092 --list
``` 

Describe group
```bash
kafka-consumer-groups --command-config conduktor.config  --bootstrap-server cluster.playground.cdkt.io:9092 --describe --group my-first-application
``` 

Dry Run (Preview result): reset the offsets to the beginning of each partition
```bash
kafka-consumer-groups --command-config conduktor.config  --bootstrap-server cluster.playground.cdkt.io:9092 --group my-first-application --reset-offsets --to-earliest --topic first_topic --dry-run
``` 

Execute offset reset
```bash
kafka-consumer-groups --command-config conduktor.config  --bootstrap-server cluster.playground.cdkt.io:9092 --group my-first-application --reset-offsets --to-earliest --topic first_topic --execute
```
