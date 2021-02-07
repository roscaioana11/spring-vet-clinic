package ro.fasttrackit.vetclinic.controller.model;

import ro.fasttrackit.vetclinic.model.Species;

import java.util.List;

public class PetInfoDto {

    public String name;
    public Species species;


    public PetInfoDto(String name,Species species) {
        this.name = name;
        this.species = species;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }


}
