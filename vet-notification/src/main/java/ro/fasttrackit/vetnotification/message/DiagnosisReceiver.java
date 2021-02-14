package ro.fasttrackit.vetnotification.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vetnotification.model.DiagnosisMessageReceiver;

@Component
@RabbitListener(queues = "diagnosis")
public class DiagnosisReceiver {

    @RabbitHandler
    public void receiveDiagnosis(String diagnosisMessage){

        ObjectMapper objectMapper = new ObjectMapper(); //cand este primit un string Json, se foloseste ObjectMapper pentru a crea un obiect din acesta

        try {
            DiagnosisMessageReceiver messageReceived = objectMapper.readValue(diagnosisMessage, DiagnosisMessageReceiver.class);
            System.out.println("ConsultationId: "+messageReceived.getConsultationId());
            System.out.println("Title: "+messageReceived.getTitle());
            System.out.println("Description: "+messageReceived.getDescription());
            System.out.println("Recommendation: "+messageReceived.getRecommendations());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
