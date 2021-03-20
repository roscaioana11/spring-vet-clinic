package ro.fasttrackit.vetclinic.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.OwnerDto;
import ro.fasttrackit.vetclinic.model.OwnerWithPetsDto;
import ro.fasttrackit.vetclinic.service.OwnerService;

import java.util.List;

@RestController
public class OwnerController {

    private final OwnerService service;

    public OwnerController(OwnerService injectedService) {

        this.service = injectedService;
    }

    @GetMapping("/api/owner")
    public List<OwnerDto> getAllOwners(){
        return service.findAllOwners();
    }

    @GetMapping("/api/owner/{id}")
    public OwnerDto findOwnerById(@PathVariable(name = "id") Long ownerId){
        return service.findOwnerById(ownerId);
    }

    @PostMapping("/api/owner/new")
    public ResponseEntity<OwnerDto> createNewOwner(@RequestBody OwnerDto ownerRequest){
        return ResponseEntity.ok(service.createNewOwner(ownerRequest));
    }

    @PutMapping("/api/owner/update")
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto updateRequest) {
        if (updateRequest.getId() == null || updateRequest.getId() <= 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(updateRequest);
        }
        return ResponseEntity.ok(service.updateOwner(updateRequest));
    }

    @GetMapping("/api/owner/allPets")
    public List<OwnerWithPetsDto> getAllOwnersWithPets(@RequestParam List<Long> ownerIds){
        return service.getOwnersWithPets(ownerIds);
    }

    @DeleteMapping("/api/owner/{id}")
    public void deleteOwner(@PathVariable("id") Long idToDelete){

        this.service.deleteOwner(idToDelete);
    }
}
