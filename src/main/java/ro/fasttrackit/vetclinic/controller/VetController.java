package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.Vet;
import ro.fasttrackit.vetclinic.service.VetService;

import java.util.List;

@RestController
public class VetController {

    private final VetService service;

    public VetController(VetService service) {
        this.service = service;
    }

    @GetMapping("/api/vet")
    public List<Vet> getAllVets(){
        return service.findAllVets();
    }

    @GetMapping("/api/vet/{id}")
    public Vet findVetById(@PathVariable(name = "id") Long vetId){
        return service.findVetById(vetId);
    }

    @PostMapping("/api/vet/new")
    public ResponseEntity<Vet> createNewVet(@RequestBody Vet vetRequest){
        return ResponseEntity.ok(service.createNewVet(vetRequest));
    }

    @PutMapping("/api/vet/update")
    public ResponseEntity<Vet> updateVet(@RequestBody Vet updateRequest){
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
