package fr.teama.stagehardwaremockservice.controllers.dto;


public class StageFullStateDTO {

    private StageDataDTO stageData;

    private Double altitude;

    private Double speed;

    public StageFullStateDTO(StageDataDTO stageData, Double altitude, Double speed) {
        this.stageData = stageData;
        this.altitude = altitude;
        this.speed = speed;
    }

    public StageFullStateDTO() {
    }

    public StageDataDTO getStageData() {
        return stageData;
    }

    public void setStageData(StageDataDTO stageData) {
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
