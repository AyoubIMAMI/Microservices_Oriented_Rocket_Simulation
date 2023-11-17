package fr.teama.robotdepartmentservice.connectors.externalDTO;


import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;

    private String serviceToBeNotified;

    private TrackingCategoryDTO category;

    private String eventDataType;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, TrackingCategoryDTO category, String eventDataType) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.category = category;
        this.eventDataType = eventDataType;
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

    public TrackingCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(TrackingCategoryDTO category) {
        this.category = category;
    }

    public String getEventDataType() {
        return eventDataType;
    }

    public void setEventDataType(String eventDataType) {
        this.eventDataType = eventDataType;
    }
}

