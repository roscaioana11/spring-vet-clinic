package ro.fasttrackit.vetclinic.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.Species;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;
import ro.fasttrackit.vetclinic.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService service;

    @Mock
    private PetRepository repository;

    @Test
    void createNewPet_expectedRepositorySaveMethodCalled() {
        Pet petRequest = new Pet(); //Given part / Test setup
        Mockito.when(this.repository.save(ArgumentMatchers.any(PetEntity.class))).thenReturn(new PetEntity());

        this.service.createNewPet(petRequest); //When the Service method calls the 'createNewStudent' method

        Mockito.verify(repository).save(ArgumentMatchers.any(PetEntity.class)); //Then the repository "save" method is called

    }

    @Test
    void createNewPet_expectedActualValuesFromRepository() {
        // Given (test setup)
        PetEntity expectedEntity = new PetEntity();
        expectedEntity.setId(1L);
        expectedEntity.setName("Luna");
        expectedEntity.setSpecies(Species.CAT);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(expectedEntity);

        // When (action to test)
        Pet actualCreatedPet = service.createNewPet(new Pet());

        // Then (assertions)
        Assertions.assertNotNull(actualCreatedPet);
        Assertions.assertEquals("Luna", actualCreatedPet.getName());
        Assertions.assertEquals(Species.CAT, actualCreatedPet.getSpecies());
    }

    @Test
    public void findAll() {
        List<PetEntity> expected = new ArrayList<>();
        expected.add(new PetEntity());

        Mockito.when(this.repository.findAll()).thenReturn(expected);

        this.service.findAllPets();

        Mockito.verify(repository).findAll();
    }

    @Test
    void findById() {
        Optional<PetEntity> testEntityId = Optional.of(new PetEntity());

        Mockito.when(this.repository.findById(1L)).thenReturn(testEntityId);

        this.service.findPetById(1L);

        Mockito.verify(repository).findById(1L);
    }

    @Test
    void deleteById() {
        Long id = 1L;

        //Mockito.when(this.repository.deleteById(1L)).thenReturn(id);

        this.service.deletePet(1L);

        Mockito.verify(repository).deleteById(1L);
    }
}