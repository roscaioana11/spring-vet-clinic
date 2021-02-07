package ro.fasttrackit.vetclinic.controller.model;

import ro.fasttrackit.vetclinic.model.Species;

public class PetDto {
    private Long id;
    private String name;
    private Species species;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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