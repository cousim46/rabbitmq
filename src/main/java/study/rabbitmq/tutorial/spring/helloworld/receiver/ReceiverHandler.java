package study.rabbitmq.tutorial.spring.helloworld.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//SpringRabbitMQConfig에 정의해놓은 큐 이름
@RabbitListener(queues = "hello")
public class ReceiverHandler {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("receive message = " + message);
    }

}
