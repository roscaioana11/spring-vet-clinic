package ro.fasttrackit.vetclinic.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "vet")
public class VetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String cnp;
    @Column
    private Date yearOfGraduation;
    @Column
    private String specialization;
    @Column
    private String phoneNumber;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ConsultationEntity> consultations;

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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Date getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(Date yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ConsultationEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationEntity> consultations) {
        this.consultations = consultations;
    }
}
