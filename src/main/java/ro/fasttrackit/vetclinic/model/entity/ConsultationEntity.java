package ro.fasttrackit.vetclinic.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "consultation")
public class ConsultationEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
//    private String diagnosis;
//
//    @Column
//    private String recommendation;
//
//    @Column
//    private String comments;
//
//    @Column
//    private Date dateOfScheduling;
//
//    @Column
//    private Date dateOfConsultation;

    @ManyToOne
    private VetEntity vet;

    @ManyToOne
    private PetEntity pet;

    @ManyToOne
    private OwnerEntity owner;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DiagnosisEntity> diagnosis;


    public List<DiagnosisEntity> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<DiagnosisEntity> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VetEntity getVet() {
        return vet;
    }

    public void setVet(VetEntity vet) {
        this.vet = vet;
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ConsultationEntity{" +
                "id=" + id +
                ", vet=" + vet +
                ", pet=" + pet +
                ", owner=" + owner +
                ", diagnosis=" + diagnosis +
                '}';
    }
}
