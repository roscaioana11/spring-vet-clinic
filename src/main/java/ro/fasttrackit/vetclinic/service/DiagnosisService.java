package ro.fasttrackit.vetclinic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Diagnosis;
import ro.fasttrackit.vetclinic.model.DiagnosisMessage;
import ro.fasttrackit.vetclinic.model.entity.DiagnosisEntity;
import ro.fasttrackit.vetclinic.repository.*;

@Service
public class DiagnosisService {

    private final DiagnosisRepository repository;
    private final ConsultationRepository consultationRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public DiagnosisService(DiagnosisRepository repository,ConsultationRepository consultationRepository,RabbitTemplate rabbitTemplate,Queue queue) {
        this.repository = repository;
        this.consultationRepository = consultationRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public Diagnosis mapEntityToDiagnosisResponse(DiagnosisEntity entity){
        Diagnosis response = new Diagnosis();
        response.setId(entity.getId());
        response.setConsultationId(entity.getConsultation().getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setRecommendations(entity.getRecommendations());
        return response;
    }

    //post
    public Diagnosis createNewDiagnosis(Diagnosis request){
        DiagnosisEntity newDiagnosis = new DiagnosisEntity();
        newDiagnosis.setConsultation(consultationRepository.findById(request.getConsultationId()).get());
        newDiagnosis.setTitle(request.getTitle());
        newDiagnosis.setDescription(request.getDescription());
        newDiagnosis.setRecommendations(request.getRecommendations());

        DiagnosisEntity saveEntity = this.repository.save(newDiagnosis);

        DiagnosisMessage diagnosisCreatedMessage = new DiagnosisMessage();
        diagnosisCreatedMessage.setTitle(newDiagnosis.getTitle());
        diagnosisCreatedMessage.setDescription(newDiagnosis.getDescription());
        diagnosisCreatedMessage.setRecommendations(newDiagnosis.getRecommendations());

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            String stringMessageConverted = objectMapper.writeValueAsString(diagnosisCreatedMessage);
            rabbitTemplate.convertAndSend(queue.getName(), stringMessageConverted);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return mapEntityToDiagnosisResponse(saveEntity);
    }
}
