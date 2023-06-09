import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BankTransactionsProducerTests {

    @Test
    public void newRandomTransactionsTest(){
        ProducerRecord<String, String> record = BankTransactionsProducer.newRandomTransaction("john");
        String key = record.key();
        String value = record.value();

        assertEquals("john", key);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(value);
            assertEquals("john", node.get("name").asText());
            assertTrue("Amount should be less than 100", node.get("amount").asInt() < 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(value);

    }

}
