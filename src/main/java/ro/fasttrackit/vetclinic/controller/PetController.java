package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;
import ro.fasttrackit.vetclinic.service.PetService;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {

    private final PetService service;

    public PetController(PetService injectedService){

        this.service = injectedService;
    }

    @GetMapping("/api/pet")
    public List<Pet> getAllPets(){
        return service.findAllPets();
    }

    @GetMapping("/api/pet/{id}")
    public Pet findPetById(@PathVariable(name = "id") Long petId){

        return service.findPetById(petId);
    }

    @PostMapping("/api/pet/new")
    public ResponseEntity<Pet> createNewPet(@RequestBody Pet petRequest){

        return ResponseEntity.ok(service.createNewPet(petRequest));
    }

    @DeleteMapping("/api/pet/{id}")
    public void deletePet(@PathVariable("id") Long idToDelete){

        this.service.deletePet(idToDelete);

    }

}


//self help info
/*@GetMapping
    public List<Pet> getAllPets(){
        return petService.getAllPets();

    @GetMapping("")
    public PetEntity<List<Pet>> getAllPets(HttpServletRequest request) {
        Long petId = (Long) request.getAttribute("petId");
        List<Pet> categories = categoryService.fetchAllCategories(userId);
        return new PetEntity<>(categories, HttpStatus.OK);
    }

      @GetMapping("/{petId}")
	public String initUpdateForm(@PathVariable("petId") Long petId, ModelMap model) {
		Pet pet = this.pets.findById(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@DeleteMapping("/{petId}")
    public PetEntity<Map<String, Boolean>> deletePet(HttpServletRequest request,
                                                               @PathVariable("petId") Long petId) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.removeCategoryWithAllTransactions(userId, categoryId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
    }*/

/*
    @GetMapping("")
    public Pet getAllPets(HttpServletRequest getRequest) {
        //List<Pet> pets = (List<Pet>) service.getAllPets();
        return service.getAllPets(HttpStatus.OK);
    }
    public ResponseEntity<List<Pet>> getAllPets(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("userId");
        List<Pet> petList = (List<Pet>) service.getAllPets(id);
        return new ResponseEntity<>(petList, HttpStatus.OK);
    }
    */