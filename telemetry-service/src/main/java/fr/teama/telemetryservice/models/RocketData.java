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

    public RocketData(List<StageData> stages) {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stages = stages;
        this.status = 1.0;
        this.acceleration = 0.0;
    }

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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public List<StageData> getStages() {
        return stages;
    }

    public void setStages(List<StageData> stages) {
        this.stages = stages;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "altitude=" + altitude +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", stages=" + stages +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }
}
