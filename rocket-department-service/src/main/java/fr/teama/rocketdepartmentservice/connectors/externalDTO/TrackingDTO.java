package fr.teama.rocketdepartmentservice.connectors.externalDTO;


import java.util.List;

public class TrackingDTO {

    private final List<TrackItemDTO> data;
    private final String serviceToBeNotified;

    private final String routeToNotify;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, String routeToNotify) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.routeToNotify = routeToNotify;
    }

    public List<TrackItemDTO> getData() {
        return data;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }

    public String getRouteToNotify() {
        return routeToNotify;
    }
}