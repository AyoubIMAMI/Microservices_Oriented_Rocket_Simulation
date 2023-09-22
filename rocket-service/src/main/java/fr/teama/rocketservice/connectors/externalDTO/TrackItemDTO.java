package fr.teama.rocketservice.connectors.externalDTO;

public class TrackItemDTO {
    private final Double data;
    private final String fieldToTrack;

    public TrackItemDTO(String fieldToTrack,Double data) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
    }

}
