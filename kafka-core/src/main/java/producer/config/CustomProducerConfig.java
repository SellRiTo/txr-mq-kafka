package producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import producer.listener.DefaultProducerListener;

import java.util.Map;

@Configuration
public class CustomProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory(KafkaProperties kafkaProperties) {

        Map<String, Object> configProps = kafkaProperties.buildProducerProperties();
        // 配置拦截器
        configProps.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "producer.interceptor.CustomProducerInterceptor");
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(configProps);
        String transactionIdPrefix = kafkaProperties.getProducer()
                .getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProducerListener<String, String> producerListener() {
        return new DefaultProducerListener();
    }


    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> factory, ProducerListener producerListener) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(factory);
        kafkaTemplate.setProducerListener(producerListener);
        return kafkaTemplate;
    }
}