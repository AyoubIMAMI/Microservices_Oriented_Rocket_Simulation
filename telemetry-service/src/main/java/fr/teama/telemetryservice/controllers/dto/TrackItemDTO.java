package fr.teama.telemetryservice.controllers.dto;

public class TrackItemDTO {
    private Double data;
    private String fieldToTrack;

    public TrackItemDTO(String fieldToTrack,Double data) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
    }

    public String getFieldToTrack() {
        return fieldToTrack;
    }

    public Double getData() {
        return data;
    }

}
