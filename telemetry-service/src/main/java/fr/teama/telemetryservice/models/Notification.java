package fr.teama.telemetryservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Optional;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String serviceToBeNotified;
    @Column(nullable = true)
    private Double height;
    @Column(nullable = true)
    private Double fuel;

    @Column(nullable = true)
    private Double status;

    public Notification( String serviceToBeNotified, Optional<Double> height, Optional<Double> fuel, Optional<Double> status) {
        this.serviceToBeNotified = serviceToBeNotified;
        height.ifPresent(aDouble -> this.height = aDouble);
        fuel.ifPresent(aDouble -> this.fuel = aDouble);
        status.ifPresent(aDouble -> this.status = aDouble);
    }
    public Notification( String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public Notification() {

    }

    public void setServiceToBeNotified(String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }

    public Double getHeight() {
        return height;
    }

    public Double getFuel() {
        return fuel;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "serviceToBeNotified='" + serviceToBeNotified + '\'' +
                ", height=" + height +
                ", fuel=" + fuel +
                ", status=" + status +
                '}';
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public Double getStatus() {
        return status;
    }
}
