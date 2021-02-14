package ro.fasttrackit.vetclinic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Diagnosis;
import ro.fasttrackit.vetclinic.model.message.DiagnosisMessageDtoSender;
import ro.fasttrackit.vetclinic.model.entity.DiagnosisEntity;
import ro.fasttrackit.vetclinic.repository.*;

@Service
public class DiagnosisService {

    private final DiagnosisRepository repository;
    private final ConsultationRepository consultationRepository;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public DiagnosisService(DiagnosisRepository repository,ConsultationRepository consultationRepository,RabbitTemplate rabbitTemplate,DirectExchange directExchange) {
        this.repository = repository;
        this.consultationRepository = consultationRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
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

        DiagnosisMessageDtoSender diagnosisCreatedMessage = new DiagnosisMessageDtoSender();
        diagnosisCreatedMessage.setConsultationId(newDiagnosis.getConsultation().getId());
        diagnosisCreatedMessage.setTitle(newDiagnosis.getTitle());
        diagnosisCreatedMessage.setDescription(newDiagnosis.getDescription());
        diagnosisCreatedMessage.setRecommendations(newDiagnosis.getRecommendations());

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            String stringMessageConverted = objectMapper.writeValueAsString(diagnosisCreatedMessage);
            rabbitTemplate.convertAndSend(directExchange.getName(), "diagnosis", stringMessageConverted);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return mapEntityToDiagnosisResponse(saveEntity);
    }
}
