package fr.teama.missionservice.connectors.externalDTO;


import fr.teama.missionservice.models.OperationType;
import fr.teama.missionservice.models.TrackingField;

public class TrackItemDTO {

    private Double data;

    private TrackingField fieldToTrack;

    private OperationType operationType;

    public TrackItemDTO(TrackingField fieldToTrack, Double data, OperationType operationType) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
        this.operationType = operationType;
    }

    public TrackItemDTO() {
    }

    public TrackingField getFieldToTrack() {
        return fieldToTrack;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public void setFieldToTrack(TrackingField fieldToTrack) {
        this.fieldToTrack = fieldToTrack;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
