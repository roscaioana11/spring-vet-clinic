package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.vetclinic.model.DiagnosisDto;
import ro.fasttrackit.vetclinic.service.DiagnosisService;

@RestController
public class DiagnosisController {

    private final DiagnosisService service;

    public DiagnosisController(DiagnosisService service) {
        this.service = service;
    }

    @PostMapping("/api/diagnosis/new")
    public ResponseEntity<DiagnosisDto> createNewDiagnosis(@RequestBody DiagnosisDto diagnosisRequest){
        return ResponseEntity.ok(service.createNewDiagnosis(diagnosisRequest));

    }
}
