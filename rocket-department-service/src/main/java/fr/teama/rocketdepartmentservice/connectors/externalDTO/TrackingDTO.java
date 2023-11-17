package fr.teama.rocketdepartmentservice.connectors.externalDTO;


import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;
    private TrackingCategoryDTO category;
    private String serviceToBeNotified;
    private String eventDataType;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, String eventDataType, TrackingCategoryDTO category) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.eventDataType = eventDataType;
        this.category = category;
    }

    public TrackingDTO(String eventDataType) {
    }

    public List<TrackItemDTO> getData() {
        return data;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }

    public String getEventDataType() {
        return eventDataType;
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

    public void setEventDataType(String eventDataType) {
        this.eventDataType = eventDataType;
    }

    public void setCategory(TrackingCategoryDTO category) {
        this.category = category;
    }
}