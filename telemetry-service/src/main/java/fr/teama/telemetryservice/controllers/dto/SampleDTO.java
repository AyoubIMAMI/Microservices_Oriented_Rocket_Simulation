package fr.teama.telemetryservice.controllers.dto;

import java.util.ArrayList;
import java.util.List;

public class SampleDTO {
    List<String> minerals;
    public SampleDTO() {
        this.minerals = new ArrayList<>();
    }
    public List<String> getMinerals() {
        return minerals;
    }
    public void addMineral(String mineral) {
        this.minerals.add(mineral);
    }
    public void setMinerals(List<String> minerals) {
        this.minerals = minerals;
    }

    @Override
    public String toString() {
        String mineralsString = "";
        for (String mineral : minerals) {
            mineralsString += mineral + ", ";
        }
        mineralsString = mineralsString.substring(0, mineralsString.length() - 2);

        return "SampleDTO{" +
                "minerals=" + mineralsString +
                '}';
    }
}
