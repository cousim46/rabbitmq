package study.rabbitmq.tutorial.spring.helloworld.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")

public class SenderController {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public SenderController(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @PostMapping
    public void send() {
        String message = "message";
        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println("message Send");
    }

}
