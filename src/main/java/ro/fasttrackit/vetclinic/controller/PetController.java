package ro.fasttrackit.vetclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.service.PetService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService service;

    public PetController(PetService injectedService){
        this.service = injectedService;
    }

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


    /*@GetMapping("")
    public Pet getAllPets(Pet petRequest) {
        //List<Pet> pets = (List<Pet>) service.getAllPets();
        return service.getAllPets(petRequest);
    }*/

    /*@GetMapping("")
    List<Pet> all(){
        return service.findAll();
    }*/

    @PostMapping("/new")
    public Pet createNewPet(@RequestBody Pet petRequest){
        return service.createNewPet(petRequest);
    }

}
