package fr.teama.payloadhadwaremockservice.models;

public class Position {
    Double x;
    Double y;
    Double altitude;

    public Position() {
        this.x = 0.0;
        this.y = 0.0;
        this.altitude = 3600.0;
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
}
