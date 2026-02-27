package study.rabbitmq.tutorial.java.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;
import study.rabbitmq.tutorial.java.workqueue.config.RabbitMqConfig;

public class Producer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            Scanner scanner = new Scanner(System.in);) {
            channel.queueDeclare("task_hello", false, false, false, null);
            while(true) {
                System.out.println("메시지 입력 : ");
                String message = scanner.nextLine();
                if(message.equalsIgnoreCase("exit")) {
                    return;
                }
                channel.basicPublish("", "task_hello", null, message.getBytes());
                System.out.println("send message = " + message);
            }
        }
    }

}
