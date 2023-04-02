import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoCooperative {

    private static final Logger log = LoggerFactory.getLogger(ConsumerDemoCooperative.class.getSimpleName());

    public static void main(String[] args) {
        String groupId = "my-java-application";
        String topic = "demo_java";


        // Create Consumer Properties
        Properties properties = new Properties();

        // Connect to Localhost
        // properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // Connect to Conduktor playground
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"2ul78UfKu4HIXrS5cYXhdB\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIydWw3OFVmS3U0SElYclM1Y1lYaGRCIiwib3JnYW5pemF0aW9uSWQiOjcwMDc1LCJ1c2VySWQiOjgxMDE3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI5ZjY3Y2ViNC0xMjEyLTQ3NjctODczNS1lM2Y0ZjhiZGZmMGYifX0.Lt6iQCeyu9UBoq18axMYOBIL77nTjCAzXX_FIaOScDo\";");

        // Set Consumer properties
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty("partition.assignment.strategy", CooperativeStickyAssignor.class.getName());

//        properties.setProperty("group.instance.id", "..."); // strategy for static assignment

        // Create Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to a topic
        consumer.subscribe(List.of(topic));

        // poll for data
        while (true){
            log.info("Polling");

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            records.forEach(record -> log.info("Key: " + record.key() + " | Value: " + record.value() + " | Partition: "+ record.partition() + " | Offset: " + record.offset()));
        }
    }
}