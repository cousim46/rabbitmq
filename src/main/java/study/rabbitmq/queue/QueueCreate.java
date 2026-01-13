package study.rabbitmq.queue;

import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class QueueCreate {
    private static final String QUEUE_NAME = "queueName";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        Queue.DeclareOk queueDeclareOk = channel.queueDeclare(QUEUE_NAME, false,false,false, Map.of());//큐 선언 및 생성
    }
}
