package ro.fasttrackit.vetclinic.service;

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

    public ConsultationService(ConsultationRepository repository,PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
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

    public ConsultationMessage createNewConsultation(Consultation request){
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
        return consultationCreatedMessage;
    }
}
