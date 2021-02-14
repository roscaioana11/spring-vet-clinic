package ro.fasttrackit.vetnotification.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ConsultationMessageReceiver {
    private String vetName;
    private String petName;
    private String ownerName;

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
