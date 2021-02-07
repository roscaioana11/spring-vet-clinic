package ro.fasttrackit.vetnotification.message;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RabbitListener(queues = "consultations2")
public class ConsultationReceiver {

    @RabbitHandler
    public void receiveConsultation(String consultationMessage){
        System.out.println(" ["+ Date.from(Instant.now())+"] Received: " + consultationMessage);
    }

}
