package mrsisa.projekat.administratorSistema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class AdministratorSistemaConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdministratorSistema(AdministratorSistemaRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            repozitorijum.save(new AdministratorSistema(
                    "sulejman",
                    "sulejman",
                    "Sulejman",
                    "Velicanstveni",
                    "sulejmanvelicanstveni@gmail.com",
                    LocalDateTime.now()
            ));


        };
    }
}
