package ro.fasttrackit.vetclinic.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.OwnerDto;
import ro.fasttrackit.vetclinic.model.OwnerWithPetsDto;
import ro.fasttrackit.vetclinic.model.entity.ConsultationEntity;
import ro.fasttrackit.vetclinic.model.entity.OwnerEntity;
import ro.fasttrackit.vetclinic.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    @Value("8082")
    private String servicePort;

    private final OwnerRepository repository;

    public OwnerService(OwnerRepository injectedRepository) {
        this.repository = injectedRepository;
    }

    public OwnerDto mapEntityToOwnerResponse(OwnerEntity entity){
        OwnerDto response = new OwnerDto();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setCnp(entity.getCnp());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setEmail(entity.getEmail());
        return response;
    }

    //for post
    public OwnerDto createNewOwner(OwnerDto request){
        OwnerEntity newOwner = new OwnerEntity();
        newOwner.setLastName(request.getLastName());
        newOwner.setFirstName(request.getFirstName());
        newOwner.setCnp(request.getCnp());
        newOwner.setPhoneNumber(request.getPhoneNumber());
        newOwner.setEmail(request.getEmail());

        OwnerEntity saveEntity = this.repository.save(newOwner);

        return mapEntityToOwnerResponse(saveEntity);
    }

    //get
    public List<OwnerDto> findAllOwners(){
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToOwnerResponse(entity))
                .collect(Collectors.toList());
    }

    //get
    public OwnerDto findOwnerById(Long ownerId){
        Optional<OwnerEntity> foundEntity = repository.findById(ownerId);
        if (!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToOwnerResponse(entityToMap))
                .get();
    }

    //put
    public OwnerDto updateOwner(OwnerDto req) {
        OwnerEntity entityToUpdate = new OwnerEntity();
        entityToUpdate.setId(req.getId()); // ! here is the diff between UPDATE and SAVE
        entityToUpdate.setLastName(req.getLastName());
        entityToUpdate.setFirstName(req.getFirstName());
        entityToUpdate.setCnp(req.getCnp());
        entityToUpdate.setPhoneNumber(req.getPhoneNumber());
        entityToUpdate.setEmail(req.getEmail());

        OwnerEntity updatedEntity = this.repository.save(entityToUpdate);

        return mapEntityToOwnerResponse(updatedEntity);
    }

    public List<OwnerWithPetsDto> getOwnersWithPets(List<Long> ownerIds){
        List<OwnerWithPetsDto> result = new ArrayList<>();
        List<OwnerEntity> ownerEntities;
        if(ownerIds.size() == 0){
            ownerEntities = this.repository.findAll();
        }else {
            ownerEntities = repository.findAllById(ownerIds);
        }
        for(int i = 0; i < ownerEntities.size(); i++){
            OwnerWithPetsDto ownerWithPets = new OwnerWithPetsDto();
            ownerWithPets.setFirstName(ownerEntities.get(i).getFirstName());
            ownerWithPets.setLastName(ownerEntities.get(i).getLastName());
            List<String> petNames = new ArrayList<>();
            List<ConsultationEntity> consultationEntities = ownerEntities.get(i).getConsultations();
            for (int j = 0; j < consultationEntities.size(); j++){
                petNames.add(consultationEntities.get(j).getPet().getName());
            }
            ownerWithPets.setPetNames(petNames);
            result.add(ownerWithPets);
        }
        return result;
    }

    //delete
    public void deleteOwner(Long id) {

        this.repository.deleteById(id);
    }

}
