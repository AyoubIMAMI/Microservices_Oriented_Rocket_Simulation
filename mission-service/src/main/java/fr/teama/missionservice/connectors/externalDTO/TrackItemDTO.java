package fr.teama.missionservice.connectors.externalDTO;

public class TrackItemDTO {
    private final Double data;
    private final String fieldToTrack;

    public TrackItemDTO(String fieldToTrack,Double data) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
    }

    public Double getData() {
        return data;
    }

    public String getFieldToTrack() {
        return fieldToTrack;
    }
}
