package com.csc340f23.assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Callie Hampton
 */
@SpringBootApplication
public class Assignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2Application.class, args);
        getCatFact();
    }

    /**
     * Get a random cat fact and make it available at this endpoint.
     */
    public static void getCatFact() {
        try {
            String url = "https://catfact.ninja/fact";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String fact = root.findValue("fact").asText();
            String length = root.findValue("length").asText();

            System.out.println("Fact: " + fact);
            System.out.println("Length: " + length);


        } catch (JsonProcessingException ex) {
            Logger.getLogger(Assignment2Application.class.getName()).log(Level.SEVERE,
                    null, ex);
            System.out.println("error in ");

        }
    }
}