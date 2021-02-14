package ro.fasttrackit.vetclinic.model.entity;

import ro.fasttrackit.vetclinic.model.Species;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "pet")
public class PetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String name;

    @Enumerated(EnumType.STRING)
    @Column private Species species;

//    @ManyToMany(mappedBy = "pets")
//    private List<OwnerEntity> owners;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private List<ConsultationEntity> consultations;

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

//    public List<OwnerEntity> getOwners() {
//        return owners;
//    }
//
//    public void setOwners(List<OwnerEntity> owners) {
//        this.owners = owners;
//    }

    public List<ConsultationEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationEntity> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species=" + species +
                ", consultations=" + consultations +
                '}';
    }
}
