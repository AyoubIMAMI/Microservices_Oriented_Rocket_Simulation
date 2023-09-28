package fr.teama.rocketdepartmentservice.connectors.externalDTO;


import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;
    private TrackingCategoryDTO category;
    private String serviceToBeNotified;
    private String routeToNotify;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, String routeToNotify, TrackingCategoryDTO category) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.routeToNotify = routeToNotify;
        this.category = category;
    }

    public TrackingDTO(String routeToNotify) {
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

    public TrackingCategoryDTO getCategory() {
        return category;
    }

    public void setData(List<TrackItemDTO> data) {
        this.data = data;
    }

    public void setServiceToBeNotified(String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public void setRouteToNotify(String routeToNotify) {
        this.routeToNotify = routeToNotify;
    }

    public void setCategory(TrackingCategoryDTO category) {
        this.category = category;
    }
}