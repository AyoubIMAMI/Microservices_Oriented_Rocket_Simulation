package fr.teama.rocketdepartmentservice.connectors.externalDTO;


public class TrackItemDTO {

    private Double data;

    private TrackingFieldDTO fieldToTrack;

    private OperationTypeDTO operationType;

    public TrackItemDTO(TrackingFieldDTO fieldToTrack, Double data, OperationTypeDTO operationType) {
        this.data = data;
        this.fieldToTrack = fieldToTrack;
        this.operationType = operationType;
    }

    public TrackItemDTO() {
    }

    public TrackingFieldDTO getFieldToTrack() {
        return fieldToTrack;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public void setFieldToTrack(TrackingFieldDTO fieldToTrack) {
        this.fieldToTrack = fieldToTrack;
    }

    public OperationTypeDTO getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeDTO operationType) {
        this.operationType = operationType;
    }
}
