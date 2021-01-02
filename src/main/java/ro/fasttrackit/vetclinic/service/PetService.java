package ro.fasttrackit.vetclinic.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;
import ro.fasttrackit.vetclinic.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Value("8081")
    private String servicePort;

    private final PetRepository repository; //mappam/injectam repository in clasa service


    public PetService(PetRepository injectedRepository) {
        this.repository = injectedRepository;
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
        Pet responseObject = new Pet(); //nu mai manipulam aceste date, le lasam immutable pe cat de mult posibil
        responseObject.setId(saveEntity.getId());
        responseObject.setName(saveEntity.getName());
        responseObject.setSpecies(saveEntity.getSpecies());
        return responseObject;
    }

    //for getmapping get all pets
    public List<PetEntity> findAll() {
            List<PetEntity> getEntityList = this.repository.findAll();
            return getEntityList;
    }

    //for getmapping get pet by id
    public Optional<PetEntity> findById(Long id) {
        Optional<PetEntity> findEntityId = this.repository.findById(id);
        return findEntityId;
    }

    //for deletemappin delete pet by id
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
