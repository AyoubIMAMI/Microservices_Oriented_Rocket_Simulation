package fr.teama.missionservice.connectors.externalDTO;

import fr.teama.missionservice.models.TrackingCategory;

import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;

    private String serviceToBeNotified;

    private TrackingCategory category;
    private String routeToNotify;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, TrackingCategory category, String routeToNotify) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.category = category;
        this.routeToNotify = routeToNotify;
    }

    public TrackingDTO() {
    }

    public List<TrackItemDTO> getData() {
        return data;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }

    public void setData(List<TrackItemDTO> data) {
        this.data = data;
    }

    public void setServiceToBeNotified(String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public TrackingCategory getCategory() {
        return category;
    }

    public void setCategory(TrackingCategory category) {
        this.category = category;
    }

    public String getRouteToNotify() {
        return routeToNotify;
    }

    public void setRouteToNotify(String routeToNotify) {
        this.routeToNotify = routeToNotify;
    }
}

