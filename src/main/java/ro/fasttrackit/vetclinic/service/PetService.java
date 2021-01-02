package ro.fasttrackit.vetclinic.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;
import ro.fasttrackit.vetclinic.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Value("8081")
    private String servicePort;

    private final PetRepository repository; //mappam/injectam repository in clasa service


    public PetService(PetRepository injectedRepository) {
        this.repository = injectedRepository;
    }

    //public List<Pet> getAllPets = new ArrayList<>();

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


    public List<PetEntity> findAll() {
        //List<PetEntity> petEntityList = new ArrayList<>();

            List<PetEntity> getEntityList = this.repository.findAll();

            return getEntityList;

        //List<Pet> responseObject = new ArrayList<>();

    }


   /* public List<Pet> findAll() {

        List<PetEntity> findEntity = this.repository.findAll();
        return findAll();
    }*/
}
