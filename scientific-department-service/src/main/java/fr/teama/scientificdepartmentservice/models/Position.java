package fr.teama.scientificdepartmentservice.models;

public class Position {
    Double x;
    Double y;
    Double altitude;

    public Position(Double x, Double y, Double altitude) {
        this.x = x;
        this.y = y;
        this.altitude = altitude;
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
        return "{" +
                "x=" + roundDouble(x) +
                ", y=" + roundDouble(y) +
                ", altitude=" + altitude +
                '}';
    }

    private Double roundDouble(Double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
