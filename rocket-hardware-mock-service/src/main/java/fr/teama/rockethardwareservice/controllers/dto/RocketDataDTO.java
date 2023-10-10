package fr.teama.rockethardwareservice.controllers.dto;

import java.util.List;

public class RocketDataDTO {

    private Double status;
    private List<StageDataDTO> stages;

    public List<StageDataDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDataDTO> stages) {
        this.stages = stages;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                ", stages=" + stages +
                ", status=" + status +
                '}';
    }
}
