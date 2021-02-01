package ro.fasttrackit.vetclinic.model.entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String cnp;

    @ManyToMany
    private List<PetEntity> pets; //numele de pets este ce punem in PetEntity la ManyToMany(mappedBy = "pets")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCNP() {
        return cnp;
    }

    public void setCNP(String CNP) {
        this.cnp = CNP;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }
}
