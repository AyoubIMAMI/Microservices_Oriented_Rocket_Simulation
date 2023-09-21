package fr.teama.telemetryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stage_data")
public class StageData {

    @Id
    @GeneratedValue
    private Long id;
    private int level;
    private double fuel;

    private boolean isActivated;

    private boolean isDetached;

    public StageData(int stageId, int level, double fuel) {
        this.level = level;
        this.fuel = fuel;
        this.isActivated = false;
        this.isDetached = false;
    }

    public StageData() {

    }

    public int getStageId() {
        return level;
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
        return "Stage{" +
                "stageId=" + level +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", isDetached=" + isDetached +
                '}';
    }
}
