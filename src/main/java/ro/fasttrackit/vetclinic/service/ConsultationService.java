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
import ro.fasttrackit.vetclinic.repository.OwnerRepository;
import ro.fasttrackit.vetclinic.repository.PetRepository;
import ro.fasttrackit.vetclinic.repository.VetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    private final ConsultationRepository repository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final OwnerRepository ownerRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public ConsultationService(ConsultationRepository repository,PetRepository petRepository,VetRepository vetRepository,OwnerRepository ownerRepository,RabbitTemplate rabbitTemplate,Queue queue) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public Consultation mapEntityToConsultationResponse(ConsultationEntity entity){
        Consultation response = new Consultation();
        response.setId(entity.getId());
        response.setVetId(entity.getVet().getId());
        response.setPetId(entity.getPet().getId());
        response.setOwnerId(entity.getOwner().getId());
        return response;
    }

    //post
    public Consultation createNewConsultation(Consultation request){
        ConsultationEntity newConsultation = new ConsultationEntity();
        Optional<ConsultationEntity> optionalConsultation = repository.findConsultationByOwnerAndPet(request.getOwnerId(),request.getPetId());
        if(optionalConsultation.isPresent()){
            newConsultation = optionalConsultation.get();
        }else {
            newConsultation.setPet(petRepository.findById(request.getPetId()).get());
            newConsultation.setOwner(ownerRepository.findById(request.getOwnerId()).get());
        }

        newConsultation.setVet(vetRepository.findById(request.getVetId()).get());
        ConsultationEntity saveEntity = this.repository.save(newConsultation);

        ConsultationMessage consultationCreatedMessage = new ConsultationMessage();
        consultationCreatedMessage.setVetName(newConsultation.getVet().getFirstName() + " " + newConsultation.getVet().getLastName());
        consultationCreatedMessage.setPetName(newConsultation.getPet().getName());
        consultationCreatedMessage.setOwnerName(newConsultation.getOwner().getFirstName() + " " + newConsultation.getOwner().getLastName());


        ObjectMapper objectMapper = new ObjectMapper(); // converteste un obiect intr-un string Json

        try { //forteaza un try-catch ca sa nu opreasca aplicatia in cazul in care conversia da fail
            String stringMessageConverted = objectMapper.writeValueAsString(consultationCreatedMessage); //acestea exista doar intre aceste acolade din cauza scopului
            rabbitTemplate.convertAndSend(queue.getName(), stringMessageConverted);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return mapEntityToConsultationResponse(saveEntity);
    }

    //get all
    public List<Consultation> findAllConsultations(){
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToConsultationResponse(entity))
                .collect(Collectors.toList());
    }

    //get
    public Consultation findConsultationById(Long consultationId){
        Optional<ConsultationEntity> foundEntity = repository.findById(consultationId);
        if(!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToConsultationResponse(entityToMap))
                .get();
    }

    //put
    public Consultation updateConsultation(Consultation req){
        ConsultationEntity entityToUpdate = new ConsultationEntity();
        entityToUpdate.setId(req.getId());
        entityToUpdate.setVet(vetRepository.findById(req.getVetId()).get());
        entityToUpdate.setPet(petRepository.findById(req.getPetId()).get());
        entityToUpdate.setOwner(ownerRepository.findById(req.getOwnerId()).get());

        ConsultationEntity updatedEntity = this.repository.save(entityToUpdate);

        return mapEntityToConsultationResponse(updatedEntity);
    }

    //delete
    public void deleteConsultation(Long id){
        this.repository.deleteById(id);
    }
}
