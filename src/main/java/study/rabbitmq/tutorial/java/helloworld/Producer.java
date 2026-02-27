package study.rabbitmq.tutorial.java.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import study.rabbitmq.tutorial.java.helloworld.config.RabbitMqConfig;
/**
 * 브로커가 충분한 디스크 여유 공간(기본적으로 최소 50MB의 여유 공간 필요)없이 시작되어 메시지를 수신하지 못할 수 있음.
 *
 * */
public class Producer {

    private static final String QUEUE_NAME = "queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        try (Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();) {
            // 큐 선언, 큐가 존재하지 않는 경우에만 생성
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Send message = " + message);
        }
    }

}
