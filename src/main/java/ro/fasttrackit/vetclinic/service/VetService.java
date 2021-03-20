package ro.fasttrackit.vetclinic.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.vetclinic.model.VetDto;
import ro.fasttrackit.vetclinic.model.entity.VetEntity;
import ro.fasttrackit.vetclinic.repository.VetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VetService {

    private final VetRepository repository;

    public VetService(VetRepository repository) {
        this.repository = repository;
    }

    public VetDto mapEntityToVetResponse(VetEntity entity){
        VetDto response = new VetDto();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setCnp(entity.getCnp());
        response.setYearOfGraduation(entity.getYearOfGraduation());
        response.setSpecialization(entity.getSpecialization());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setEmail(entity.getEmail());
        return response;
    }

    //post
    public VetDto createNewVet(VetDto request){
        VetEntity newVet = new VetEntity();
        newVet.setFirstName(request.getFirstName());
        newVet.setLastName(request.getLastName());
        newVet.setCnp(request.getCnp());
        newVet.setYearOfGraduation(request.getYearOfGraduation());
        newVet.setSpecialization(request.getSpecialization());
        newVet.setPhoneNumber(request.getPhoneNumber());
        newVet.setEmail(request.getEmail());

        VetEntity saveEntity = this.repository.save(newVet);

        return mapEntityToVetResponse(saveEntity);
    }

    //get
    public List<VetDto> findAllVets(){
        return this.repository.findAll()
                .stream()
                .map(entity -> mapEntityToVetResponse(entity))
                .collect(Collectors.toList());
    }

    //get
    public VetDto findVetById(Long vetId){
        Optional<VetEntity> foundEntity = repository.findById(vetId);
        if(!foundEntity.isPresent()){
            return null;
        }
        return foundEntity
                .map(entityToMap -> mapEntityToVetResponse(entityToMap))
                .get();
    }

    //put
    public VetDto updateVet(VetDto req){
        VetEntity entityToUpdate = new VetEntity();
        entityToUpdate.setId(req.getId()); // this is the diff between UPDATE and SAVE
        entityToUpdate.setFirstName(req.getFirstName());
        entityToUpdate.setLastName(req.getLastName());
        entityToUpdate.setCnp(req.getCnp());
        entityToUpdate.setYearOfGraduation(req.getYearOfGraduation());
        entityToUpdate.setSpecialization(req.getSpecialization());
        entityToUpdate.setPhoneNumber(req.getPhoneNumber());
        entityToUpdate.setEmail(req.getEmail());

        VetEntity updatedEntity = this.repository.save(entityToUpdate);

        return mapEntityToVetResponse(updatedEntity);
    }

    //delete
    public void deleteVet(Long id){
        this.repository.deleteById(id);
    }
}
