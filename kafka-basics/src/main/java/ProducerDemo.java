import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello world!");

        // Create Producer Properties
        Properties properties = new Properties();

        // Connect to Localhost
        // properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // Connect to Conduktor playground
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"2ul78UfKu4HIXrS5cYXhdB\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIydWw3OFVmS3U0SElYclM1Y1lYaGRCIiwib3JnYW5pemF0aW9uSWQiOjcwMDc1LCJ1c2VySWQiOjgxMDE3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI5ZjY3Y2ViNC0xMjEyLTQ3NjctODczNS1lM2Y0ZjhiZGZmMGYifX0.Lt6iQCeyu9UBoq18axMYOBIL77nTjCAzXX_FIaOScDo\";");

        // Set Producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Create a Producer Record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "hello world");

        // Send Data
        producer.send(producerRecord);

        // Tell Producer to send all data and block until done -- Synchronous
        producer.flush();

        // Close the producer
        producer.close();
    }
}