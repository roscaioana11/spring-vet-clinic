package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.PetDto;
import ro.fasttrackit.vetclinic.service.PetService;

import java.util.List;

@RestController
public class PetController {

    private final PetService service;

    public PetController(PetService injectedService){

        this.service = injectedService;
    }

    @GetMapping("/api/pet")
    public List<PetDto> getAllPets(){
        return service.findAllPets();
    }

    @GetMapping("/api/pet/{id}")
    public PetDto findPetById(@PathVariable(name = "id") Long petId){

        return service.findPetById(petId);
    }

    @PostMapping("/api/pet/new")
    public ResponseEntity<PetDto> createNewPet(@RequestBody PetDto petRequest){

        return ResponseEntity.ok(service.createNewPet(petRequest));
    }

    @PutMapping("/api/pet/update")
    public ResponseEntity<PetDto> updatePet(@RequestBody PetDto updateRequest) {
        if (updateRequest.getId() == null || updateRequest.getId() <= 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(updateRequest);
        }
        return ResponseEntity.ok(service.updatePet(updateRequest));
    }

    @DeleteMapping("/api/pet/{id}")
    public void deletePet(@PathVariable("id") Long idToDelete){

        this.service.deletePet(idToDelete);

    }
}


