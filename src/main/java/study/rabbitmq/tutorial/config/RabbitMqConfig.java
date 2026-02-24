package study.rabbitmq.tutorial.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public class RabbitMqConfig {
    public static ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

}
