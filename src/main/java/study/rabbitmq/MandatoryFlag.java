package study.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class MandatoryFlag {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("BasicReturnExchange", BuiltinExchangeType.DIRECT);
        channel.addReturnListener((replyCode, replyText, exchange,
            routingKey, properties, body) -> {
            System.out.println("=== Message Returned ===");
            System.out.println("replyCode   : " + replyCode);
            System.out.println("replyText   : " + replyText);
            System.out.println("exchange    : " + exchange);
            System.out.println("routingKey : " + routingKey);
            System.out.println("body        : " + new String(body));
        });
        byte[] message = "BasicReturnMessage".getBytes();
        channel.basicPublish("BasicReturnExchange", "routingKey", true, new BasicProperties(),message);
    }
}
