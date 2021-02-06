package ro.fasttrackit.vetclinic.controller.model;

public class OwnerDto {

    public Long ownerId;
    public String newFirstName;
    public String newLastName;

    public OwnerDto(Long ownerId,String newFirstName,String newLastName) {
        this.ownerId = ownerId;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }
}
