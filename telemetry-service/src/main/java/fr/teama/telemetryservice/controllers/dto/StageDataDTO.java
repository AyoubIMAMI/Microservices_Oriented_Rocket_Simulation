package fr.teama.telemetryservice.controllers.dto;

public class StageDataDTO {

    private int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private boolean isDetached;

    public StageDataDTO(int stageLevel, double fuel) {
        this.stageLevel = stageLevel;
        this.fuel = fuel;
        this.isActivated = false;
        this.isDetached = false;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public StageDataDTO() {
    }

    public int getStageLevel() {
        return stageLevel;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setDetached(boolean detached) {
        isDetached = detached;
    }

    public boolean isDetached() {
        return isDetached;
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                '}';
    }
}
