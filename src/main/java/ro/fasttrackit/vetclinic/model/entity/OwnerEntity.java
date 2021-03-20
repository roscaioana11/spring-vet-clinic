package ro.fasttrackit.vetclinic.model.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "owner")
public class OwnerEntity implements Serializable {

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
    private String phoneNumber;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<ConsultationEntity> consultations;

//    @ManyToMany
//    private List<PetEntity> pets; //numele de pets este ce punem in PetEntity la ManyToMany(mappedBy = "pets")

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
