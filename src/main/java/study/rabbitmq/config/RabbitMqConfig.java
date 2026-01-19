package study.rabbitmq.config;


import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConfig {
    public static ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }
}
