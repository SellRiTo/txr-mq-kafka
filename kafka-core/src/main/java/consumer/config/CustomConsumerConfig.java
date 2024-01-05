package consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/4 15:00
 */
@Configuration
public class CustomConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties) {

        Map<String, Object> configProps = kafkaProperties.buildConsumerProperties();
        // 配置拦截器
        configProps.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, "consumer.interceptor.CustomConsumerInterceptor");
        DefaultKafkaConsumerFactory<Object, Object> objectObjectDefaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(configProps);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
