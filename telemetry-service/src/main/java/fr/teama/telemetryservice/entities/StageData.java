package fr.teama.telemetryservice.entities;

import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class StageData {

    @NotNull(message = "Stage must have a level")
    private int stageLevel;
    private Double fuel;
    private boolean isActivated;
    private boolean isDetached;

    public StageData(int stageLevel, Double fuel) {
        this.stageLevel = stageLevel;
        this.fuel = fuel;
        this.isActivated = false;
        this.isDetached = false;
    }

    public StageData() {

    }

    public StageData(StageDataDTO stageDTO) {
        this.stageLevel=stageDTO.getStageLevel();
        this.fuel=stageDTO.getFuel();
        this.isActivated=stageDTO.isActivated();
        this.isDetached=stageDTO.isDetached();
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

    public boolean isDetached() {
        return isDetached;
    }

    public void setDetached(boolean detached) {
        isDetached = detached;
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", isDetached=" + isDetached +
                '}';
    }
}
