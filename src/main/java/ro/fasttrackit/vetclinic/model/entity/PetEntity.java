package ro.fasttrackit.vetclinic.model.entity;

import ro.fasttrackit.vetclinic.model.Species;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "pet")
public class PetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String name;

    @Enumerated(EnumType.STRING)
    @Column private Species species;



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

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species=" + species +
                '}';
    }


}
