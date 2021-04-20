package mrsisa.projekat.dermatolog;

import mrsisa.projekat.poseta.Poseta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                    null,
                    generisiPosete(0),
                    "300"
            ));
            repozitorijum.save(new Dermatolog(
                    "nikola",
                    "nikola",
                    "Nikola",
                    "Nikolic",
                    "nikolanikolic@gmail.com",
                    LocalDateTime.now(),
                    null,
                    generisiPosete(1),
                    "250"
            ));
            repozitorijum.save(new Dermatolog(
                    "matija",
                    "matija",
                    "Matija",
                    "Matija",
                    "matijamatija@gmail.com",
                    LocalDateTime.now(),
                    null,
                    generisiPosete(2),
                    "200"
            ));
        };
    }


    private static ArrayList<Poseta> generisiPosete(int i){
        ArrayList<Poseta> list = new ArrayList<Poseta>();
        Poseta p = new Poseta();
        p.setPocetak(LocalDateTime.now());
        p.setKraj(LocalDateTime.now().plusMinutes(30));
        list.add(p);
        return list;
    }
}
