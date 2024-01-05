package annotation;

import consumer.config.CustomConsumerConfig;
import org.springframework.context.annotation.Import;
import producer.config.CustomProducerConfig;

import java.lang.annotation.*;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/4 15:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Import({CustomConsumerConfig.class, CustomProducerConfig.class})
public @interface EnableCustomKafka {
}
