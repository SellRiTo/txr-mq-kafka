package producer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/5 9:52
 */
@Slf4j
public class DefaultProducerListener implements ProducerListener<String, String> {


    @Override
    public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
        log.info("message sent successfully: topic【{}】,key【{}】", producerRecord.topic(), producerRecord.key());
    }

    @Override
    public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {
        log.error("message sent successfully: topic【{}】,key【{}】,value【{}】", producerRecord.topic(), producerRecord.key(), producerRecord.value());
    }
}
