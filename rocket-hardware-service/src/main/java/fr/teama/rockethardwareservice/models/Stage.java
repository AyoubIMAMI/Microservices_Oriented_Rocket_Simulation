package fr.teama.rockethardwareservice.models;

public class Stage {

    private final int stageId;

    private Double fuel;

    private boolean isActivated;

    private boolean isDetached;

    public Stage(int stageId, double fuel) {
        this.stageId = stageId;
        this.fuel = fuel;
        this.isActivated = false;
        this.isDetached = false;
    }

    public int getStageId() {
        return stageId;
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

    @Override
    public String toString() {
        return "Stage{" +
                "stageId=" + stageId +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", isDetached=" + isDetached +
                '}';
    }
}
