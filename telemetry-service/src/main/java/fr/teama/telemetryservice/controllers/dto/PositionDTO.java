package fr.teama.telemetryservice.controllers.dto;

public class PositionDTO {
    Double x;
    Double y;
    Double altitude;

    public PositionDTO() {
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

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", altitude=" + altitude ;
    }
}
