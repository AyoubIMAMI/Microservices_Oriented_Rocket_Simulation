package fr.teama.telemetryservice.entities;

import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rocket_data")
public class RocketData {

    @Id
    @GeneratedValue
    private Long id;
    private Double altitude;
    private Double speed;
    @OneToMany
    private List<StageData> stages;

    private LocalDateTime timestamp;

    public RocketData(List<StageData> stages) {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stages = stages;
    }

    public RocketData(RocketDataDTO rocketDTO) {
        this.altitude=rocketDTO.getAltitude();
        this.speed=rocketDTO.getSpeed();
        this.stages=new ArrayList<>();
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

    @Override
    public String toString() {
        return "RocketData{" +
                "altitude=" + altitude +
                ", speed=" + speed +
                ", stages=" + stages +
                ", timestamp=" + timestamp +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
