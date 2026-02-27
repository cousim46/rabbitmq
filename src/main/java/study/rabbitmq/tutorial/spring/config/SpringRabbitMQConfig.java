package study.rabbitmq.tutorial.spring.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.rabbitmq.tutorial.spring.helloworld.receiver.Receiver;
import study.rabbitmq.tutorial.spring.helloworld.sender.Sender;

@Configuration
public class SpringRabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }


}
