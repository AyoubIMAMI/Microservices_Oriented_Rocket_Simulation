package fr.teama.payloadservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Optional;

@Entity
public class PayloadData {
    @GeneratedValue
    @Id
    private Long id;
    private Double height;
    private Double angle;

    public PayloadData(String serviceToBeNotified, Double height, Double angle) {
        this.height = height;
        this.angle=angle;
    }

    public PayloadData() {

    }


    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
