package fr.teama.telemetryservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import javax.swing.text.html.Option;
import java.util.Optional;

@Entity
public class Notification {
    @Id
    private Long id;
    private String serviceToBeNotified;
    @Column(nullable = true)
    private Double height;
    @Column(nullable = true)

    private Double fuel;

    public Notification( String serviceToBeNotified, Optional<Double> height, Optional<Double> fuel) {
        this.serviceToBeNotified = serviceToBeNotified;
        height.ifPresent(aDouble -> this.height = aDouble);
        fuel.ifPresent(aDouble -> this.fuel = aDouble);
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
}
