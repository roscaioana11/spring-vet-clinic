package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.Consultation;
import ro.fasttrackit.vetclinic.service.ConsultationService;

import java.util.List;

@RestController
public class ConsultationController {

    private final ConsultationService service;

    public ConsultationController(ConsultationService injectedService) {
        this.service = injectedService;
    }

    @GetMapping("/api/consultation")
    public List<Consultation> getAllConsultations(){
        return service.findAllConsultations();
    }

    @GetMapping("/api/consultation/{id}")
    public Consultation findConsultationById(@PathVariable(name = "id") Long consultationId){
        return service.findConsultationById(consultationId);
    }

    @PostMapping("/api/consultation/new")
    public ResponseEntity<Consultation> createNewConsultation(@RequestBody Consultation consultationRequest){
        return ResponseEntity.ok(service.createNewConsultation(consultationRequest));

    }

    @PutMapping("/api/consultation/update")
    public ResponseEntity<Consultation> updateConsultation(@RequestBody Consultation updateRequest){
        if(updateRequest.getId() == null || updateRequest.getId() <= 0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(updateRequest);
        }
        return ResponseEntity.ok(service.updateConsultation(updateRequest));
    }

    @DeleteMapping("/api/consultation/{id}")
    public void deleteConsultation(@PathVariable("id") Long idToDelete){
        this.service.deleteConsultation(idToDelete);
    }
}
