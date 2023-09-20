package fr.teama.rockethardwareservice;

import fr.teama.rockethardwareservice.interfaces.IHardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketHardwareMockApplication {

    @Autowired
    private IHardware hardware;

    public static void main(String[] args) {
        SpringApplication.run(RocketHardwareMockApplication.class, args);
    }

}
