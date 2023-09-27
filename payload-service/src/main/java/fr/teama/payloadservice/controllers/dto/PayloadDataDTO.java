package fr.teama.payloadservice.controllers.dto;


import java.util.Optional;

public class PayloadDataDTO {
    private Double height;
    private Double angle;

    public PayloadDataDTO(String serviceToBeNotified, Double height, Double angle) {
        this.height = height;
        this.angle=angle;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }
}

