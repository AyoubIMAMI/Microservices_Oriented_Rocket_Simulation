package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.TrackingDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tracking {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<TrackItem> data;

    private String serviceToBeNotified;

    private String eventDataType;

    @Enumerated
    private TrackingCategory category;

    public Tracking(List<TrackItem> data, String requestService, String eventDataType, TrackingCategory category) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.category = category;
    }

    public Tracking(TrackingDTO trackingDTO) {
        this.data = new ArrayList<>();
        trackingDTO.getData().forEach(trackItemDTO -> this.data.add(new TrackItem(trackItemDTO)));
        this.serviceToBeNotified = trackingDTO.getServiceToBeNotified();
        this.category = trackingDTO.getCategory();
        this.eventDataType = trackingDTO.getEventDataType();
    }

    public Tracking() {

    }

    public void setServiceToBeNotified(String serviceToBeNotified) {
        this.serviceToBeNotified = serviceToBeNotified;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }

    public List<TrackItem> getData() {
        return data;
    }

    public void setData(List<TrackItem> data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "Tracking{" +
                "data=" + data +
                ", serviceToBeNotified='" + serviceToBeNotified + '\'' +
                ", category=" + category +
                '}';
    }
}
