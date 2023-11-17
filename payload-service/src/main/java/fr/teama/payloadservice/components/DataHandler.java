package fr.teama.payloadservice.components;

import fr.teama.payloadservice.models.PayloadData;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.PayloadDataHandler;
import fr.teama.payloadservice.interfaces.proxy.IPayloadHardwareProxy;
import fr.teama.payloadservice.repository.PayloadDataRepository;
import fr.teama.payloadservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataHandler implements IDataAsker, PayloadDataHandler {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private PayloadDataRepository payloadDataRepository;

    @Autowired
    private IPayloadHardwareProxy payloadHardwareProxy;

    @Override
    public ResponseEntity<String> askOrbitToTelemetry() {
        kafkaProducerService.missionStartNotify();
        return ResponseEntity.ok().body("Telemetry notified");
    }
    @Override
    public void saveDataPayload(PayloadData payloadData) throws PayloadHardwareServiceUnavaibleException {
        payloadDataRepository.save(payloadData);
        LoggerHelper.logInfo(payloadData.toString());
        List<PayloadData> payloadDataList = payloadDataRepository.findAllByRocketName(payloadData.getRocketName());
        LoggerHelper.logInfo("Number of payload data: " + payloadDataList.size());
        if (payloadDataList.size()>=10){
            boolean certifyOrbit=true;
            LoggerHelper.logInfo("Sample of 10 payload data available");
            LoggerHelper.logInfo("Verifying that the payload altitude was always between 3500 and 3900");
            for (PayloadData currentPayloadData: payloadDataList){
                LoggerHelper.logInfo(currentPayloadData.toString());
                if (currentPayloadData.getPosition().getAltitude()>3500 && currentPayloadData.getPosition().getAltitude()<3900){
                    LoggerHelper.logInfo("Altitude: " +currentPayloadData.getPosition().getAltitude()+" between 3500 and 3900");
                }
                else{
                    LoggerHelper.logInfo("Altitude: " +currentPayloadData.getPosition().getAltitude()+" not between 3500 and 3900");
                    certifyOrbit=false;
                    break;
                }
            }
            if(certifyOrbit)
                LoggerHelper.logInfo("The orbital parameters desired by the customer are ensured!");
            else
                LoggerHelper.logInfo("The payload is not on the orbital parameters desired by the customer.");
            payloadHardwareProxy.stopOrbitalPosDispatch();
        }

    }

    @Override
    public ResponseEntity<String> resetDB() {
        payloadDataRepository.deleteAll();
        return ResponseEntity.ok().body("Database reset");
    }
}
