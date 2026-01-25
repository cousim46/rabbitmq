package study.rabbitmq;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class AlterExchangeConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String alterExchangeName = "alterExchangeName";
        channel.exchangeDeclare(alterExchangeName, BuiltinExchangeType.FANOUT);
        DeclareOk declareOk = channel.queueDeclare();
        channel.queueBind(declareOk.getQueue(), alterExchangeName, "");
        channel.basicConsume(declareOk.getQueue(), false,
            (consumerTag, delivery) -> {
                System.out.println(new String(delivery.getBody(), StandardCharsets.UTF_8));
            },
            consumerTag -> {
                System.out.println("소비자 취소됨");
            });
    }
}
