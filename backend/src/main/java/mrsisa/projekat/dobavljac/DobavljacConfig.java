package mrsisa.projekat.dobavljac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DobavljacConfig {

    @Bean
    CommandLineRunner commandLineRunnerDobavljac(DobavljacRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            repozitorijum.save(new Dobavljac(
                    "mika",
                    "mika",
                    "Mika",
                    "Mikic",
                    "mikamikic@gmail.com",
                    LocalDateTime.now()
            ));
            repozitorijum.save(new Dobavljac(
                    "pera",
                    "pera",
                    "Pera",
                    "Peric",
                    "peraperic@gmail.com",
                    LocalDateTime.now()

            ));

        };
    }
}
