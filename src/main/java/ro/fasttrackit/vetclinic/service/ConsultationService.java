package ro.fasttrackit.vetclinic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Consultation;
import ro.fasttrackit.vetclinic.model.ConsultationMessage;
import ro.fasttrackit.vetclinic.model.entity.ConsultationEntity;
import ro.fasttrackit.vetclinic.repository.ConsultationRepository;
import ro.fasttrackit.vetclinic.repository.PetRepository;

import java.util.stream.Collectors;

@Service
public class ConsultationService {

    private final ConsultationRepository repository;
    private final PetRepository petRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public ConsultationService(ConsultationRepository repository,PetRepository petRepository,RabbitTemplate rabbitTemplate,Queue queue) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public Consultation mapEntityToConsultationResponse(ConsultationEntity entity){
        Consultation response = new Consultation();
        response.setId(entity.getId());
        response.setDiagnosis(entity.getDiagnosis());
        response.setRecommendation(entity.getRecommendation());
        response.setComments(entity.getComments());
        response.setDateOfScheduling(entity.getDateOfScheduling());
        response.setDateOfConsultation(entity.getDateOfConsultation());
        response.setPetId(entity.getPet().getId());
        return response;
    }

    public Consultation createNewConsultation(Consultation request){
        ConsultationEntity newConsultation = new ConsultationEntity();
        newConsultation.setDiagnosis(request.getDiagnosis());
        newConsultation.setRecommendation(request.getRecommendation());
        newConsultation.setComments(request.getComments());
        newConsultation.setDateOfScheduling(request.getDateOfScheduling());
        newConsultation.setDateOfConsultation(request.getDateOfConsultation());
        newConsultation.setPet(petRepository.findById(request.getPetId()).get());

        ConsultationEntity saveEntity = this.repository.save(newConsultation);

        ConsultationMessage consultationCreatedMessage = new ConsultationMessage();
        consultationCreatedMessage.setDateOfConsultation(newConsultation.getDateOfConsultation());
        consultationCreatedMessage.setDateOfScheduling(newConsultation.getDateOfScheduling());
        consultationCreatedMessage.setPetName(newConsultation.getPet().getName());
        consultationCreatedMessage.setOwnerNames(newConsultation.getPet().getOwners()
                .stream()
                .map(owner -> new String(owner.getFirstName()) + " " + owner.getLastName())
                .collect(Collectors.toList()));

        ObjectMapper objectMapper = new ObjectMapper(); // converteste un obiect intr-un string Json

        try { //forteaza un try-catch ca sa nu opreasca aplicatia in cazul in care conversia da fail
            String stringMessageConverted = objectMapper.writeValueAsString(consultationCreatedMessage); //acestea exista doar intre aceste acolade din cauza scopului
            rabbitTemplate.convertAndSend(queue.getName(), stringMessageConverted);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return mapEntityToConsultationResponse(saveEntity);
    }
}
