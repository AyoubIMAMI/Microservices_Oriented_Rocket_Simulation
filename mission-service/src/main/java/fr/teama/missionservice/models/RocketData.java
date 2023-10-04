package fr.teama.missionservice.models;

import java.util.List;

public class RocketData {

    private List<StageData> stages;
    private Double status;

    public RocketData(List<StageData> stages, Double status) {
        this.stages = stages;
        this.status = status;
    }

    public List<StageData> getStages() {
        return stages;
    }

    public void setStages(List<StageData> stages) {
        this.stages = stages;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }
}
