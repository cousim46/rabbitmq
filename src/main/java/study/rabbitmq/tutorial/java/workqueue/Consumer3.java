package study.rabbitmq.tutorial.java.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import study.rabbitmq.tutorial.java.workqueue.config.RabbitMqConfig;

public class Consumer3 {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = RabbitMqConfig.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("=========Consumer3 동작=========");
        channel.queueDeclare("task_hello", false, false, false, null);
//        channel.basicQos(1); //한 번에 하나의 ack(확인 응답)되지 않은 메시지만 수신합니다.
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Consumer3 message = " + message);
            try {
                doWork(message);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };
        boolean autoAck = false;
        channel.basicConsume("task_hello", autoAck, deliverCallback, conumserTag -> {
        });
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(10000);
            }
        }
    }

}
