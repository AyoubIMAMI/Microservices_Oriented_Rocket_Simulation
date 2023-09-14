package fr.teama.missionservice.components;

import fr.teama.missionservice.interfaces.IPollMaker;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PollMaker implements IPollMaker {

    @Override
    public ResponseEntity<String> startMission() {
        String baseUrl = "http://localhost:8080/api";
        String weatherStatus = getRequest(baseUrl + "/weather");
        String rocketStatus = getRequest(baseUrl + "/rocket");

        if (weatherStatus.equals("GO") && rocketStatus.equals("GO"))
            return ResponseEntity.ok().body("GO");
        else
            return ResponseEntity.ok().body("NO GO");
    }

    // @TODO move
    String getRequest(String apiUrl) {
        try {
            // Créez une URL à partir de l'URL de l'API
            URL url = new URL(apiUrl);

            // Ouvrez une connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurez la méthode de requête HTTP (GET dans ce cas)
            connection.setRequestMethod("GET");

            // Obtenez le code de réponse HTTP
            int responseCode = connection.getResponseCode();

            // Vérifiez si la requête s'est bien passée (code de réponse 200)
            if (responseCode == 200) {
                // Créez un lecteur pour lire la réponse de l'API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Lisez la réponse ligne par ligne
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Affichez la réponse de l'API
                System.out.println("Réponse de l'API : " + response.toString());
                return response.toString();
            } else {
                System.out.println("La requête a échoué avec le code de réponse : " + responseCode);
            }

            // Fermez la connexion HTTP
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "NO GO";
    }
}
