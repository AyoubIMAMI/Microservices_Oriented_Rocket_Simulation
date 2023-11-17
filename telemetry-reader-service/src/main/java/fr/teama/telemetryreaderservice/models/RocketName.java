package fr.teama.telemetryreaderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RocketName {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public RocketName() {
    }

    public RocketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }
}
