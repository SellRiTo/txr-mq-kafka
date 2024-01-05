package producer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/4 14:17
 */
@Slf4j
public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        log.info("开始往topic【】，发送消息：【{}】", producerRecord.topic(), producerRecord.value());
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

        if (null == e) {
            log.info("发送kafka消息成功: topic:【{}】,offset:【{}】,partition:【{}】", recordMetadata.topic(), recordMetadata.offset(), recordMetadata.partition());
        } else {
            log.error("发送kafka消息失败：topic:【{}】,offset:【{}】,partition:【{}】,错误原因：{}", recordMetadata.topic(), recordMetadata.offset(), recordMetadata.partition(), e.getMessage());
        }
    }

    @Override
    public void close() {
        log.info("接收到kafka关闭事件,close CustomProducerInterceptor");
    }

    @Override
    public void configure(Map<String, ?> map) {
        // 在拦截器被配置时调用
        log.info("Configuring interceptor with configs: {}", map);
        log.info("恭喜恭喜, {} 已经被注册进去了", CustomProducerInterceptor.class.getName());
    }
}
