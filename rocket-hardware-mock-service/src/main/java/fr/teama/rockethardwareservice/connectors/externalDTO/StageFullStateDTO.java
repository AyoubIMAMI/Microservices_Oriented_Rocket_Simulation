package fr.teama.rockethardwareservice.connectors.externalDTO;

import fr.teama.rockethardwareservice.models.Position;
import fr.teama.rockethardwareservice.models.StageData;

public class StageFullStateDTO {

    private StageData stageData;

    private Position position;

    private Double speed;

    public StageFullStateDTO(StageData stageData, Position position, Double speed) {
        this.stageData = stageData;
        this.position = position;
        this.speed = speed;
    }

    public StageFullStateDTO() {
    }

    public StageData getStageData() {
        return stageData;
    }

    public void setStageData(StageData stageData) {
        this.stageData = stageData;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "StageFullStateDTO{" +
                "position=" + position +
                ", stageData=" + stageData +
                ", speed=" + speed +
                '}';
    }
}
