package consumer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Iterator;
import java.util.Map;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/4 14:17
 */
@Slf4j
public class CustomConsumerInterceptor implements ConsumerInterceptor<String, String> {


    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
        Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
        while (iterator.hasNext()) {
            ConsumerRecord<String, String> next = iterator.next();
            log.info("从topic【{}】，接收到消息：【{}】", next.topic(), next.value());
        }
        return consumerRecords;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        // 在提交消费位移之前调用
        log.info("Committing offsets");
    }

    @Override
    public void close() {
        log.info("接收到kafka关闭事件,close CustomConsumerInterceptor");
    }

    @Override
    public void configure(Map<String, ?> map) {
        // 在拦截器被配置时调用
        log.info("Configuring interceptor with configs: {}", map);
        log.info("恭喜恭喜, {} 已经被注册进去了", CustomConsumerInterceptor.class.getName());
    }
}
