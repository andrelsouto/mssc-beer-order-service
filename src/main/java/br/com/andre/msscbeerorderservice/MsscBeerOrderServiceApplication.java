package br.com.andre.msscbeerorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class MsscBeerOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerOrderServiceApplication.class, args);
    }

}
