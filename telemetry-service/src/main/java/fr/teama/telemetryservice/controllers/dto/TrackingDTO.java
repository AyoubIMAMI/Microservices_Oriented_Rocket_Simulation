package fr.teama.telemetryservice.controllers.dto;


import java.util.List;

public class TrackingDTO {

    private List<TrackItemDTO> data;
    private String serviceToBeNotified;

    public TrackingDTO(List<TrackItemDTO> data, String requestService) {
        this.data = data;
        this.serviceToBeNotified = requestService;
    }

    public TrackingDTO() {
    }

    public List<TrackItemDTO> getData() {
        return data;
    }

    public String getServiceToBeNotified() {
        return serviceToBeNotified;
    }


}

