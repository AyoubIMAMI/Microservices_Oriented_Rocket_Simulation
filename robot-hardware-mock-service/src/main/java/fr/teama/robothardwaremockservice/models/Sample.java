package fr.teama.robothardwaremockservice.models;

import java.util.ArrayList;
import java.util.List;

public class Sample {
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

}
