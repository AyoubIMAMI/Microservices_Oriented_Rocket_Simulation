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

    @ElementCollection
    private List<TrackItem> data;

    private String serviceToBeNotified;

    @Enumerated
    private TrackingCategory category;

    public Tracking(List<TrackItem> data, String requestService, TrackingCategory category) {
        this.data = data;
        this.serviceToBeNotified = serviceToBeNotified;
        this.category = category;
    }

    public Tracking(TrackingDTO trackingDTO) {
        this.data = new ArrayList<>();
        trackingDTO.getData().forEach(trackItemDTO -> this.data.add(new TrackItem(trackItemDTO)));
        this.serviceToBeNotified = trackingDTO.getServiceToBeNotified();
        this.category = trackingDTO.getCategory();
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

    @Override
    public String toString() {
        return "Tracking{" +
                "data=" + data +
                ", serviceToBeNotified='" + serviceToBeNotified + '\'' +
                ", category=" + category +
                '}';
    }
}
