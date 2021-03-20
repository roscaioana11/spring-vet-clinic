package ro.fasttrackit.vetclinic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.ConsultationDto;
import ro.fasttrackit.vetclinic.model.message.ConsultationMessageDtoSender;
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
    private final DirectExchange directExchange;

    public ConsultationService(ConsultationRepository repository,PetRepository petRepository,VetRepository vetRepository,OwnerRepository ownerRepository,RabbitTemplate rabbitTemplate,DirectExchange directExchange) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public ConsultationDto mapEntityToConsultationResponse(ConsultationEntity entity){
        ConsultationDto response = new ConsultationDto();
        response.setId(entity.getId());
        response.setVetId(entity.getVet().getId());
        response.setPetId(entity.getPet().getId());
        response.setOwnerId(entity.getOwner().getId());
        return response;
    }

    //post
    public ConsultationDto createNewConsultation(ConsultationDto request){
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

        ConsultationMessageDtoSender consultationCreatedMessage = new ConsultationMessageDtoSender();
        consultationCreatedMessage.setVetName(newConsultation.getVet().getFirstName() + " " + newConsultation.getVet().getLastName());
        consultationCreatedMessage.setPetName(newConsultation.getPet().getName());
        consultationCreatedMessage.setOwnerName(newConsultation.getOwner().getFirstName() + " " + newConsultation.getOwner().getLastName());


        ObjectMapper objectMapper = new ObjectMapper(); // converteste un obiect intr-un string Json

        try { //forteaza un try-catch ca sa nu opreasca aplicatia in cazul in care conversia da fail
            String stringMessageConverted = objectMapper.writeValueAsString(consultationCreatedMessage); //acestea exista doar intre aceste acolade din cauza scopului
            rabbitTemplate.convertAndSend(directExchange.getName(), "consultation", stringMessageConverted);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return mapEntityToConsultationResponse(saveEntity);
    }

    //get all
    public List<ConsultationDto> findAllConsultations(){
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToConsultationResponse(entity))
                .collect(Collectors.toList());
    }

    //get
    public ConsultationDto findConsultationById(Long consultationId){
        Optional<ConsultationEntity> foundEntity = repository.findById(consultationId);
        if(!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToConsultationResponse(entityToMap))
                .get();
    }

    //put
//    public ConsultationDto updateConsultation(ConsultationDto req){
//        ConsultationEntity entityToUpdate = new ConsultationEntity();
//        entityToUpdate.setId(req.getId());
//        entityToUpdate.setVet(vetRepository.findById(req.getVetId()).get());
//        entityToUpdate.setPet(petRepository.findById(req.getPetId()).get());
//        entityToUpdate.setOwner(ownerRepository.findById(req.getOwnerId()).get());
//
//        ConsultationEntity updatedEntity = this.repository.save(entityToUpdate);
//
//        return mapEntityToConsultationResponse(updatedEntity);
//    }

    //delete
    public void deleteConsultation(Long id){
        this.repository.deleteById(id);
    }
}
