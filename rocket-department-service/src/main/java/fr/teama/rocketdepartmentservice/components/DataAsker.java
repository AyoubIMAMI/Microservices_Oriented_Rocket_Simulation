package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.OperationTypeDTO;
import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackingFieldDTO;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    /*
     * Log template:
     * "the <service/hardware> <verb> <data tracked> of"
     * example: "the rocket reaches the fuel of"
     */
    @Override
    public void getNotificationOnEvents() {
        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the fuel is empty");
        kafkaProducerService.askWhenEventHappens(TrackingFieldDTO.FUEL, 40.0, "rocket-department",
                "/rocket/stage", OperationTypeDTO.LESS_OR_EQUAL, "the rocket reaches the fuel of ");

        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the rocket enters the Max Q");
        kafkaProducerService.askWhenEventHappens(TrackingFieldDTO.HEIGHT, 1300.0, "rocket-department",
                "/rocket/enters-q", OperationTypeDTO.GREATER_OR_EQUAL, "the rocket reaches the altitude of ");

        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the rocket leaves the Max Q");
        kafkaProducerService.askWhenEventHappens(TrackingFieldDTO.HEIGHT, 1800.0, "rocket-department",
                "/rocket/leaves-q", OperationTypeDTO.GREATER_OR_EQUAL, "the rocket reaches the altitude of ");

        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the rocket reaches the orbit altitude");
        kafkaProducerService.askWhenEventHappens(TrackingFieldDTO.HEIGHT, 3000.0, "rocket-department",
                "/rocket/fairing-altitude", OperationTypeDTO.GREATER_OR_EQUAL, "the rocket reaches the altitude of ");
    }
}
