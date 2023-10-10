package fr.teama.stagehardwaremockservice.controllers.dto;


public class StageFullStateDTO {

    private StageDataDTO stageData;

    private PositionDTO position;

    private Double speed;

    public StageFullStateDTO() {
    }

    public StageDataDTO getStageData() {
        return stageData;
    }

    public void setStageData(StageDataDTO stageData) {
        this.stageData = stageData;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
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
