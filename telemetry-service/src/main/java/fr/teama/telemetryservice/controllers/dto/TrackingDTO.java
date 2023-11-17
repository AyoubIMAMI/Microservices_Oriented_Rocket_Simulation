package fr.teama.telemetryservice.controllers.dto;


import fr.teama.telemetryservice.models.TrackingCategory;

import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;

    private String serviceToBeNotified;

    private TrackingCategory category;

    private String eventDataType;

    public TrackingDTO(List<TrackItemDTO> data, String requestService, String eventDataType, TrackingCategory category) {
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

    public TrackingCategory getCategory() {
        return category;
    }

    public void setCategory(TrackingCategory category) {
        this.category = category;
    }

    public String getEventDataType() {
        return eventDataType;
    }

    public void setEventDataType(String eventDataType) {
        this.eventDataType = eventDataType;
    }
}

