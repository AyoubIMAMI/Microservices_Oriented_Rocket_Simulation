package fr.teama.rockethardwareservice.controllers.dto;

public class StageDataDTO {

    private int stageLevel;
    private Double fuel;

    public int getStageLevel() {
        return stageLevel;
    }

    public void setStageLevel(int stageLevel) {
        this.stageLevel = stageLevel;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }
}
