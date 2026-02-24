package study.rabbitmq.tutorial.config;


import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConfig {
    public static ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        return connectionFactory;
    }

}
