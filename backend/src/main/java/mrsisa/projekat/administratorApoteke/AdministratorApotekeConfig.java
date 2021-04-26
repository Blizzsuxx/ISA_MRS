package mrsisa.projekat.administratorApoteke;

import mrsisa.projekat.uloga.Uloga;
import mrsisa.projekat.uloga.UlogaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
@Configuration
public class AdministratorApotekeConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdministratorApoteke(AdministratorApotekeRepository repozitorijum,
                                                            UlogaRepository repozitorijumUloga){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return args ->{
            repozitorijum.save(new AdministratorApoteke(
                    "dunja",
                    "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra",
                    "Dunja",
                    "Dunjica",
                    "dunjadunjica@gmail.com",
                    LocalDateTime.now(),
                    "ROLE_ADMIN_APOTEKE",
                    true,
                    Timestamp.valueOf("2017-10-01 21:58:58")
            ));
            repozitorijum.save(new AdministratorApoteke(
                    "visnja",
                    "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra",
                    "Visnja",
                    "Visnjica",
                    "visnjavisnjica@gmail.com",
                    LocalDateTime.now(),
                    "ROLE_ADMIN_APOTEKE",
                    true,
                    Timestamp.valueOf("2017-10-01 21:58:58")
            ));

        };
    }
}
*/