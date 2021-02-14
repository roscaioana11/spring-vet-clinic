package ro.fasttrackit.vetclinic.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fasttrackit.vetclinic.model.Owner;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.entity.OwnerEntity;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;
import ro.fasttrackit.vetclinic.repository.PetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Value("8082")
    private String servicePort;

    private final PetRepository repository; //mappam/injectam repository in clasa service


    public PetService(PetRepository injectedRepository) {
        this.repository = injectedRepository;
    }

    public  Pet mapEntityToPetResponse(PetEntity entity){
        Pet response = new Pet();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setSpecies(entity.getSpecies());
        return response;
    }

    //for postmapping adding a new pet in db
    public Pet createNewPet(Pet request){

        //cream o entitate
        PetEntity newPet = new PetEntity();
        newPet.setName(request.getName());
        newPet.setSpecies(request.getSpecies());

        //salvam entitatea
        PetEntity saveEntity = this.repository.save(newPet);

        //cream manual un response object
        return mapEntityToPetResponse(saveEntity);
    }

    //for getmapping get all pets
    public List<Pet> findAllPets() {
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToPetResponse(entity))
                .collect(Collectors.toList());
    }

    //for getmapping get pet by id
    public Pet findPetById(Long petId) {
        Optional<PetEntity> foundEntity = repository.findById(petId);
        if (!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToPetResponse(entityToMap))
                .get();
    }

    public Pet updatePet(Pet req) {
        PetEntity entityToUpdate = new PetEntity();
        entityToUpdate.setId(req.getId()); // ! here is the diff between UPDATE and SAVE
        entityToUpdate.setName(req.getName());
        entityToUpdate.setSpecies(req.getSpecies());

        PetEntity updatedEntity = this.repository.save(entityToUpdate);

        return mapEntityToPetResponse(updatedEntity);
    }

    //for deletemappin delete pet by id
    public void deletePet(Long id) {

        this.repository.deleteById(id);
    }
}
