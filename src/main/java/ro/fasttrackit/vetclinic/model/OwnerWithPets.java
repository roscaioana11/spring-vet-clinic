package ro.fasttrackit.vetclinic.model;

import java.util.List;

public class OwnerWithPets {
    private String firstName;
    private String lastName;
    private List<String> petNames;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPetNames() {
        return petNames;
    }

    public void setPetNames(List<String> petNames) {
        this.petNames = petNames;
    }

    @Override
    public String toString() {
        return "OwnerWithPets{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", petNames=" + petNames +
                '}';
    }
}
