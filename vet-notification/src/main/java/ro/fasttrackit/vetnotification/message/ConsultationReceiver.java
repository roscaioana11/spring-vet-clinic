package ro.fasttrackit.vetnotification.message;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "consultations")
public class ConsultationReceiver {

    @RabbitHandler
    public void receiveConsultation(String rawMessage){
        System.out.println(" [>] Received: " + rawMessage);
    }

}
