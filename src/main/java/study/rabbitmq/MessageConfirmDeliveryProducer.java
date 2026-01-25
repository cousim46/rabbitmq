package study.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Confirm.SelectOk;
import com.rabbitmq.client.AMQP.Exchange.DeclareOk;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class MessageConfirmDeliveryProducer {
    public static void main(String[] args)
        throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DeclareOk exchange = channel.exchangeDeclare("exchange",
            BuiltinExchangeType.DIRECT);//익스체인지 선언
        SelectOk selectOk = channel.confirmSelect();
        channel.basicPublish("exchange","routingKey", new BasicProperties(), new String("메시지").getBytes() );
        channel.waitForConfirmsOrDie(); // 또는 channel.waitForConfirms()
    }
}
