package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.vetclinic.model.Consultation;
import ro.fasttrackit.vetclinic.service.ConsultationService;

@RestController
public class ConsultationController {

    private final ConsultationService service;

    public ConsultationController(ConsultationService injectedService) {
        this.service = injectedService;
    }

    @PostMapping("/api/consultation/new")
    public ResponseEntity<Consultation> createNewConsultation(@RequestBody Consultation consultationRequest){
        return ResponseEntity.ok(service.createNewConsultation(consultationRequest));

    }
}
