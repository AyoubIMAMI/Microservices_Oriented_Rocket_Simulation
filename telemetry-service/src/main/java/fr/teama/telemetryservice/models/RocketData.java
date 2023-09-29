package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RocketData {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<StageData> stages;

    private Double altitude;

    private Double speed;

    private Double acceleration;

    private LocalDateTime timestamp;

    private Double status;

    public RocketData(RocketDataDTO rocketDTO) {
        this.altitude=rocketDTO.getAltitude();
        this.speed=rocketDTO.getSpeed();
        this.stages=new ArrayList<>();
        this.status = rocketDTO.getStatus();
        rocketDTO.getStages().forEach(stageDTO -> this.stages.add(new StageData(stageDTO)));
    }

    public RocketData() {

    }

    public StageData getStageByLevel(int stageLevel){
        for (StageData stage: stages){
            if (stage.getStageLevel()==stageLevel)
                return stage;
        }
        return null;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "stages=" + stages +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

    public List<StageData> getStages() {
        return stages;
    }

    public void setStages(List<StageData> stages) {
        this.stages = stages;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }
}
