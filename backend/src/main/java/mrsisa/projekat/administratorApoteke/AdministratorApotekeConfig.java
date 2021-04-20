package mrsisa.projekat.administratorApoteke;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class AdministratorApotekeConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdministratorApoteke(AdministratorApotekeRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return args ->{
            repozitorijum.save(new AdministratorApoteke(
                    "dunja",
                    "dunja",
                    "Dunja",
                    "Dunjica",
                    "dunjadunjica@gmail.com",
                    LocalDateTime.now()
            ));
            repozitorijum.save(new AdministratorApoteke(
                    "visnja",
                    "visnja",
                    "Visnja",
                    "Visnjica",
                    "visnjavisnjica@gmail.com",
                    LocalDateTime.now()

            ));

        };
    }
}
