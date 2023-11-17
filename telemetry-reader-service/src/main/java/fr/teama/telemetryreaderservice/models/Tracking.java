package fr.teama.telemetryreaderservice.models;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tracking {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<TrackItem> data;

    private String serviceToBeNotified;

    private String routeToNotify;

    @Enumerated
    private TrackingCategory category;

    public Tracking(List<TrackItem> data, String requestService, String routeToNotify, TrackingCategory category) {
        this.data = data;
        this.serviceToBeNotified = requestService;
        this.category = category;
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

    public String getRouteToNotify() {
        return routeToNotify;
    }

    public void setRouteToNotify(String routeToNotify) {
        this.routeToNotify = routeToNotify;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "data=" + data +
                ", serviceToBeNotified='" + serviceToBeNotified + '\'' +
                ", category=" + category +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
