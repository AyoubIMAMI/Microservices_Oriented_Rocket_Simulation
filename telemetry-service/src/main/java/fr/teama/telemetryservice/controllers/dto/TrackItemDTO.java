package fr.teama.telemetryservice.controllers.dto;

public class TrackItemDTO {
    private Double data;
    private String fieldToTrack;

    public TrackItemDTO(String fieldToTrack,Double data) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
    }

    public TrackItemDTO() {
    }

    public String getFieldToTrack() {
        return fieldToTrack;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public void setFieldToTrack(String fieldToTrack) {
        this.fieldToTrack = fieldToTrack;
    }
}
