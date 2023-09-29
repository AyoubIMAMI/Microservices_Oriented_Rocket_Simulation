package fr.teama.rockethardwareservice.connectors.externalDTO;

import fr.teama.rockethardwareservice.models.StageData;

public class StageFullStateDTO {

    private StageData stageData;

    private Double altitude;

    private Double speed;

    public StageFullStateDTO(StageData stageData, Double altitude, Double speed) {
        this.stageData = stageData;
        this.altitude = altitude;
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

    @Override
    public String toString() {
        return "StageFullStateDTO{" +
                "stageData=" + stageData +
                ", altitude=" + altitude +
                ", speed=" + speed +
                '}';
    }
}
