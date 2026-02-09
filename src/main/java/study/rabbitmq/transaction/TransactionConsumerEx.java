package study.rabbitmq.transaction;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class TransactionConsumerEx {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel(); //통신할 채널 오픈
        channel.txSelect();
        channel.queueDeclare("queue", false, false, false, null);
        channel.queueBind("queue", "exchange", "");
        channel.basicConsume("queue", false,
            (DeliverCallback) (consumerTag, message) -> {
                System.out.println("메시지 소비시간 : "  + LocalDateTime.now());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                System.out.println(
                    "consumer  commit consumerTag = " + consumerTag + ", message = "
                        + new String(message.getBody()));
            }, (CancelCallback) consumerTag -> {
                System.out.println("cancel consumerTag = " + consumerTag);
            });
    }
}
