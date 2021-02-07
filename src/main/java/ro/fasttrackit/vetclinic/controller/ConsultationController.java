package ro.fasttrackit.vetclinic.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.vetclinic.controller.model.ConsultationDto;
import ro.fasttrackit.vetclinic.controller.model.ConsultationMessageDtoSender;
import ro.fasttrackit.vetclinic.model.Consultation;
import ro.fasttrackit.vetclinic.model.ConsultationMessage;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.entity.ConsultationEntity;
import ro.fasttrackit.vetclinic.service.ConsultationService;

import java.io.Serializable;

@RestController
@Component
public class ConsultationController {

    private final ConsultationService service;
    private final RabbitTemplate template;
    public final Queue queue;

    public ConsultationController(ConsultationService injectedService,RabbitTemplate template,Queue queue) {
        this.service = injectedService;
        this.template = template;
        this.queue = queue;
    }

    public Consultation mapDtoToConsultationResponse(ConsultationDto dto){
        Consultation response = new Consultation();
        response.setId(dto.getId());
        response.setDiagnosis(dto.getDiagnosis());
        response.setRecommendation(dto.getRecommendation());
        response.setComments(dto.getComments());
        response.setDateOfScheduling(dto.getDateOfScheduling());
        response.setDateOfConsultation(dto.getDateOfConsultation());
        response.setPetId(dto.getPetId());
        return response;
    }

    public ConsultationMessageDtoSender mapConsultationMessageToDto(ConsultationMessage message){
        ConsultationMessageDtoSender response = new ConsultationMessageDtoSender();
        response.setDateOfScheduling(message.getDateOfScheduling());
        response.setDateOfConsultation(message.getDateOfConsultation());
        response.setPetName(message.getPetName());
        response.setOwnerNames(message.getOwnerNames());
        return response;
    }

    @PostMapping("/api/consultation/new")
    public ResponseEntity<ConsultationMessageDtoSender> createNewConsultation(@RequestBody ConsultationDto consultationRequest){
        ConsultationMessageDtoSender messageDtoSender = mapConsultationMessageToDto(service.createNewConsultation(mapDtoToConsultationResponse(consultationRequest)));
        this.template.convertAndSend(queue.getName(), messageDtoSender.toString()); //the sender -> SerializationUtils.serialize(messageDtoSender) => converts the message to byte (serialize)
        return ResponseEntity.ok(messageDtoSender);
    }
}
