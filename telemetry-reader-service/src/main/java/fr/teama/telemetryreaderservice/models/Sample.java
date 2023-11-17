package fr.teama.telemetryreaderservice.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Sample {
    @ElementCollection
    List<String> minerals;
    public Sample() {
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
        return "SampleDTO{" +
                "minerals=" + mineralsString +
                '}';
    }

}
