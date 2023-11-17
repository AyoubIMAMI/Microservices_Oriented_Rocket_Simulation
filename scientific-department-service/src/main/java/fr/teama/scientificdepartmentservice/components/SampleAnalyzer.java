package fr.teama.scientificdepartmentservice.components;

import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import fr.teama.scientificdepartmentservice.interfaces.ISampleAnalyzer;
import fr.teama.scientificdepartmentservice.models.Sample;
import org.springframework.stereotype.Component;

@Component
public class SampleAnalyzer implements ISampleAnalyzer {
    @Override
    public void analyzeSample(Sample sample) {
        LoggerHelper.logInfo("Sample analyzed: " + sample.toString());
        LoggerHelper.logInfo("New Discovery: there might be presence of Water!");
    }
}
