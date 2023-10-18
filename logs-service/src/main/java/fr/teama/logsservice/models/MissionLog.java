package fr.teama.logsservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class MissionLog {
    @Id
    @GeneratedValue
    private Long id;

    private String rocketName;

    private String serviceName;

    private String text;

    private LocalDateTime date;

    public MissionLog() {
    }

    public MissionLog(String rocketName, String serviceName, String text, LocalDateTime date) {
        this.rocketName = rocketName;
        this.serviceName = serviceName;
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString() {
        return "MissionLog{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }
}
