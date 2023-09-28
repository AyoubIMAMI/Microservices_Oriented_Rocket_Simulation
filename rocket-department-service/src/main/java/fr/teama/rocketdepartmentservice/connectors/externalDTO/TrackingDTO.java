package fr.teama.rocketdepartmentservice.connectors.externalDTO;


import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;

    private String serviceToBeNotified;

    private final String routeToNotify;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, String routeToNotify) {
    private TrackingCategoryDTO category;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, TrackingCategoryDTO category) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.routeToNotify = routeToNotify;
        this.category = category;
    }

    public TrackingDTO() {
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

    public void setData(List<TrackItemDTO> data) {
        this.data = data;
    }

    public void setServiceToBeNotified(String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public TrackingCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(TrackingCategoryDTO category) {
        this.category = category;
    }
}