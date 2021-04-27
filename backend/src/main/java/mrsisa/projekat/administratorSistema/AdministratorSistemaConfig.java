package mrsisa.projekat.administratorSistema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
@Configuration
public class AdministratorSistemaConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdministratorSistema(AdministratorSistemaRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            repozitorijum.save(new AdministratorSistema(
                    "sulejman",
                    "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra",
                    "Sulejman",
                    "Velicanstveni",
                    "sulejmanvelicanstveni@gmail.com",
                    LocalDateTime.now(),
                    "ROLE_ADMIN_SISTEMA",
                    true,
                    Timestamp.valueOf("2017-10-01 21:58:58")
            ));


        };
    }
}
*/