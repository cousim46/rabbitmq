package study.rabbitmq.transaction;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;
import study.rabbitmq.config.RabbitMqConfig;

public class TransactionProducerEx {

    public static void main(String[] args)
        throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel(); //통신할 채널 오픈
        channel.txSelect();// 트랜잭션 시작
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("exchange", "", new BasicProperties(), String.format("rabbitmq transaction %d", i + 1).getBytes());
            Thread.sleep(1000);
            System.out.println((i + 1) + "초..");
        }
        try {
            System.out.println("발행자 ㅁ커밋 시간 : "  + LocalDateTime.now());
            channel.txCommit(); // 트랜잭션 커밋
        }catch (IOException e){
            channel.txRollback();
            System.out.println("트랜잭션 예외 발생하는 경우");
        }
    }
}
