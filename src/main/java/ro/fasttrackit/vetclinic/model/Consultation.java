package ro.fasttrackit.vetclinic.model;

import java.util.Date;

public class Consultation {

    private Long id;
    private String diagnosis;
    private String recommendation;
    private String comments;
    private Date dateOfScheduling;
    private Date dateOfConsultation;

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

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", comments='" + comments + '\'' +
                ", dateOfScheduling=" + dateOfScheduling +
                ", dateOfConsultation=" + dateOfConsultation +
                '}';
    }
}
