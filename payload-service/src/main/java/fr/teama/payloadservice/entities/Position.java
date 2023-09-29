package fr.teama.payloadservice.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Position {
    Double x;
    Double y;
    Double altitude;

    public Position(Double x, Double y, Double altitude) {
        this.x = x;
        this.y = y;
        this.altitude = altitude;
    }

    public Position() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", altitude=" + altitude;
    }
}
