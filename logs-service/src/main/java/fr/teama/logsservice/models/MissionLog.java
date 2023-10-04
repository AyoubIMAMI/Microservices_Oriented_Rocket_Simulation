package fr.teama.logsservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MissionLog {
    @Id
    private Long id;
    private String serviceName;
    private String text;
    private double timestamp;

    public MissionLog() {
    }

    public MissionLog(String serviceName, String text) {
        this.serviceName = serviceName;
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
