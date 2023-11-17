package fr.teama.telemetryreaderservice.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

@Embeddable
public class TrackItem {

    private Double data;

    @Enumerated
    private TrackingField fieldToTrack;

    @Enumerated
    private OperationType operationType;

    public TrackItem(TrackingField fieldToTrack, Double data, OperationType operationType) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
        this.operationType = operationType;
    }

    public TrackItem() {
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

    @Override
    public String toString() {
        return "TrackItem{" +
                "data=" + data +
                ", fieldToTrack=" + fieldToTrack +
                ", operationType=" + operationType +
                '}';
    }

    public boolean verifyCondition(Double dataToCheck) {
        switch (operationType) {
            case EQUAL:
                return dataToCheck.equals(data);
            case GREATER:
                return dataToCheck > data;
            case LESS:
                return dataToCheck < data;
            case GREATER_OR_EQUAL:
                return dataToCheck >= data;
            case LESS_OR_EQUAL:
                return dataToCheck <= data;
            case NOT_EQUAL:
                return !dataToCheck.equals(data);
            default:
                return false;
        }
    }
}
