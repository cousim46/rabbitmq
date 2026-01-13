package study.rabbitmq.exchange;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ExchangeCreate {
    private static final String EXCHANGE_NAME = "exchangeName";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        Exchange.DeclareOk exchangeDeclareOk = channel.exchangeDeclare(EXCHANGE_NAME,
            BuiltinExchangeType.DIRECT);//익스체인지 선언 및 생성
        byte[] message = "메시지".getBytes(StandardCharsets.UTF_8);
        channel.basicPublish(EXCHANGE_NAME, "routingKey", null, message);
    }

}
