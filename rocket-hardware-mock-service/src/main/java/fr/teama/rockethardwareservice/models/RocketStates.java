package fr.teama.rockethardwareservice.models;

public enum RocketStates {
    NORMAL(0.0),
    SEVERE_ANOMALY(1.0),
    PRESSURE_ANOMALY(2.0);

    private double value;

    RocketStates(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
