package ro.fasttrackit.vetclinic.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.Owner;
import ro.fasttrackit.vetclinic.model.entity.OwnerEntity;
import ro.fasttrackit.vetclinic.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    @Value("8081")
    private String servicePort;

    private final OwnerRepository repository;

    public OwnerService(OwnerRepository injectedRepository) {
        this.repository = injectedRepository;
    }

    public Owner mapEntityToOwnerResponse(OwnerEntity entity){
        Owner response = new Owner();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setCnp(entity.getCNP());
        return response;
    }

    public Owner createNewOwner(Owner request){
        OwnerEntity newOwner = new OwnerEntity();
        newOwner.setLastName(request.getLastName());
        newOwner.setFirstName(request.getFirstName());
        newOwner.setCNP(request.getCnp());

        OwnerEntity saveEntity = this.repository.save(newOwner);

        return mapEntityToOwnerResponse(saveEntity);
    }

    public List<Owner> findAllOwners(){
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToOwnerResponse(entity))
                .collect(Collectors.toList());
    }

    public Owner findOwnerById(Long ownerId){
        Optional<OwnerEntity> foundEntity = repository.findById(ownerId);
        if (!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToOwnerResponse(entityToMap))
                .get();
    }

    public Owner updateOwner(Owner req) {
        OwnerEntity entityToUpdate = new OwnerEntity();
        entityToUpdate.setId(req.getId()); // ! here is the diff between UPDATE and SAVE
        entityToUpdate.setLastName(req.getLastName());
        entityToUpdate.setFirstName(req.getFirstName());
        entityToUpdate.setCNP(req.getCnp());

        OwnerEntity updatedEntity = this.repository.save(entityToUpdate);

        return mapEntityToOwnerResponse(updatedEntity);
    }

    public void deleteOwner(Long id) {

        this.repository.deleteById(id);
    }

}
