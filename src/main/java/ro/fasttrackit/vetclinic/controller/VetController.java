package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.VetDto;
import ro.fasttrackit.vetclinic.service.VetService;

import java.util.List;

@RestController
public class VetController {

    private final VetService service;

    public VetController(VetService service) {
        this.service = service;
    }

    @GetMapping("/api/vet")
    public List<VetDto> getAllVets(){
        return service.findAllVets();
    }

    @GetMapping("/api/vet/{id}")
    public VetDto findVetById(@PathVariable(name = "id") Long vetId){
        return service.findVetById(vetId);
    }

    @PostMapping("/api/vet/new")
    public ResponseEntity<VetDto> createNewVet(@RequestBody VetDto vetRequest){
        return ResponseEntity.ok(service.createNewVet(vetRequest));
    }

    @PutMapping("/api/vet/update")
    public ResponseEntity<VetDto> updateVet(@RequestBody VetDto updateRequest){
        if(updateRequest.getId() == null || updateRequest.getId() <= 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(updateRequest);
        }
        return  ResponseEntity.ok(service.updateVet(updateRequest));
    }

    @DeleteMapping("/api/api/vet/{id}")
    public void deleteVet(@PathVariable("id") Long idToDelete){
        this.service.deleteVet(idToDelete);
    }
}
