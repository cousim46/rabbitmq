package study.rabbitmq.tutorial.java.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import study.rabbitmq.tutorial.java.config.RabbitMqConfig;
/**
 * 발행자보다 소비자가 먼저시작 될 수 있습니다.
 * 큐에서 메시지를 소비하기 전에 큐가 존재하는지 확인해야합니다.
 * try-with-resrouces 방식으로 하면 프로그램이 다음단계로 넘어가면서 모든것을 닫고 종료될 수 있습니다.
 * */
public class Consumer {
    private static final String QUEUE_NAME = "queue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 메시지를 사용할 준비가 될때까지 버퍼링할 객체 형태의 콜백 제공해주는 클래스 : DeliverCallback
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messsage = new String(delivery.getBody(), "UTF-8");
            System.out.println("Receive messsage = " + messsage);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }

}
