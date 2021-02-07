package ro.fasttrackit.vetclinic.message;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class ConsultationSender {

    private final RabbitTemplate template;
    public final Queue queue;

    public ConsultationSender(RabbitTemplate template,Queue queue) {
        this.template = template;
        this.queue = queue;
    }
//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
//    public void sendMessageEverySecond() {
//        String message = "Hello world ";
//        this.template.convertAndSend(queue.getName(), message);
//        System.out.println(" [x] Sent: '" + message + "'");
//    }
}
