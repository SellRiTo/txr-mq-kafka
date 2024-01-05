package config;

import annotation.EnableCustomKafka;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tangxianrui
 * @version 1.0
 * @date 2024/1/4 15:06
 */
@Order(1)
@Configuration
@EnableCustomKafka
public class CustomKafkaConfig {


}
