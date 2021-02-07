package ro.fasttrackit.vetclinic.controller.model;

import java.util.Date;

public class ConsultationDto {
    private Long id;
    private String diagnosis;
    private String recommendation;
    private String comments;
    private Date dateOfScheduling;
    private Date dateOfConsultation;
    private Long petId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateOfScheduling() {
        return dateOfScheduling;
    }

    public void setDateOfScheduling(Date dateOfScheduling) {
        this.dateOfScheduling = dateOfScheduling;
    }

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
