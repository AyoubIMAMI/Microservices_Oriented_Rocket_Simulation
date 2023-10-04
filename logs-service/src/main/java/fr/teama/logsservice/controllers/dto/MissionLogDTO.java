package fr.teama.logsservice.controllers.dto;

public class MissionLogDTO {
    String serviceName;
    String text;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
