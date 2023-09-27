package fr.teama.rocketdepartmentservice.connectors.externalDTO;

public class TrackItemDTO {
    private final Double data;
    private final String fieldToTrack;
    private final String routeToNotify;

    public TrackItemDTO(String fieldToTrack, Double data, String routeToNotify) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
        this.routeToNotify = routeToNotify;
    }

    public Double getData() {
        return data;
    }

    public String getFieldToTrack() {
        return fieldToTrack;
    }

    public String getRouteToNotify() {
        return routeToNotify;
    }
}
