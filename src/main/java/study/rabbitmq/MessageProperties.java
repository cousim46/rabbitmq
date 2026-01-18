package study.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class MessageProperties {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("exchangeName", "routingKey",
            new BasicProperties(
                "contentType",
                "contentEncoding",
                null,
                1,
                0,
                "correlationId",
                "repltTo",
                "expiration",
                "messageId",
                Date.from(Instant.now()),
                "type",
                "userId",
                "appId",
                "clusterId"
            ),
            new byte[]{}
        );
    }
}
