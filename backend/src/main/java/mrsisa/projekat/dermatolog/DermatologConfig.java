package mrsisa.projekat.dermatolog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DermatologConfig {

    @Bean
    CommandLineRunner commandLineRunnerDermatolog(DermatologRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            repozitorijum.save(new Dermatolog(
                    "marko",
                    "marko",
                    "Marko",
                    "Markovic",
                    "markomarkovic@gmail.com",
                    LocalDateTime.now(),
                    "300"
            ));
            repozitorijum.save(new Dermatolog(
                    "nikola",
                    "nikola",
                    "Nikola",
                    "Nikolic",
                    "nikolanikolic@gmail.com",
                    LocalDateTime.now(),
                    "250"
            ));
            repozitorijum.save(new Dermatolog(
                    "matija",
                    "matija",
                    "Matija",
                    "Matija",
                    "matijamatija@gmail.com",
                    LocalDateTime.now(),
                    "200"
            ));
        };
    }
}
