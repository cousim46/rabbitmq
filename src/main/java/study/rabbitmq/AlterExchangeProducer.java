package study.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class AlterExchangeProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String alterExchangeName = "alterExchangeName";
        channel.exchangeDeclare(alterExchangeName, BuiltinExchangeType.FANOUT);

        Map<String, Object> argMap = new HashMap<>();
        argMap.put("alternate-exchange", alterExchangeName);
        channel.exchangeDeclare("baseExchangeName", BuiltinExchangeType.DIRECT, false,
            false, argMap);

        channel.basicPublish("baseExchangeName","routingKey", new BasicProperties(), "대체 익스체인지에서 처리됨".getBytes());
    }
}
