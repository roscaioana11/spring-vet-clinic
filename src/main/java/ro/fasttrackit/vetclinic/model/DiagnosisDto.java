package ro.fasttrackit.vetclinic.model;

public class DiagnosisDto {
    private Long id;
    private Long consultationId;
    private String title;
    private String description;
    private String recommendations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public String toString() {
        return "DiagnosisDto{" +
                "id=" + id +
                ", consultationId=" + consultationId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", recommendations='" + recommendations + '\'' +
                '}';
    }
}
