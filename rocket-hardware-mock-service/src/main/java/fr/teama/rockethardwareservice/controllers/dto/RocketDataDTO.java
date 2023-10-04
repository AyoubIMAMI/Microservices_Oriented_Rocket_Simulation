package fr.teama.rockethardwareservice.controllers.dto;

import fr.teama.rockethardwareservice.models.StageData;

import java.util.List;

public class RocketDataDTO {

    private List<StageData> stages;
    private Double status;

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
