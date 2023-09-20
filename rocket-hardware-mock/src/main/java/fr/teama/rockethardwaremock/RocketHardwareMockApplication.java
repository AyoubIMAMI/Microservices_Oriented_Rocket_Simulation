package fr.teama.rockethardwaremock;

import fr.teama.rockethardwaremock.interfaces.IHardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketHardwareMockApplication implements CommandLineRunner {

    @Autowired
    private IHardware hardware;

    public static void main(String[] args) {
        SpringApplication.run(RocketHardwareMockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        hardware.startSendData();
    }

}
