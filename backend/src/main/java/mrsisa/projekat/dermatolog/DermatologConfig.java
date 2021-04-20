package mrsisa.projekat.dermatolog;

import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
import mrsisa.projekat.radnoVrijeme.RadnoVrijemeRepository;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Configuration
public class DermatologConfig {

    @Bean
    @Transactional
    CommandLineRunner commandLineRunnerDermatolog(DermatologRepository repozitorijum, ApotekaRepository apotekaRepository,
                                                  RadnoVrijemeRepository radnoVrijemeRepository, SlobodanTerminRepository slobodanTerminRepository){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            RadnoVrijeme radnoVrijeme =  new RadnoVrijeme();
            radnoVrijeme.setApoteka(apotekaRepository.findById(1L).orElse(null));

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
            radnoVrijeme.setDermatolog(repozitorijum.findById(4).orElse(null));
            radnoVrijeme.setPocetakRadnogVremena(LocalTime.of(10,00));
            radnoVrijeme.setKrajRadnogVremena(LocalTime.of(17,00));
            radnoVrijemeRepository.save(radnoVrijeme);
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

            SlobodanTermin slobodanTermin = new SlobodanTermin();
            slobodanTermin.setDermatolog(repozitorijum.findById(4).orElse(null));
            slobodanTermin.setApoteka(apotekaRepository.findById(1L).orElse(null));
            slobodanTermin.setPocetakTermina(LocalTime.of(10,00));
            slobodanTermin.setKrajTermina(LocalTime.of(11,00));
            slobodanTermin.setCijenaTermina(100);
            slobodanTerminRepository.save(slobodanTermin);
            SlobodanTermin slobodanTermin1 = new SlobodanTermin();
            slobodanTermin1.setDermatolog(repozitorijum.findById(4).orElse(null));
            slobodanTermin1.setApoteka(apotekaRepository.findById(1L).orElse(null));
            slobodanTermin1.setPocetakTermina(LocalTime.of(11,00));
            slobodanTermin1.setKrajTermina(LocalTime.of(12,00));
            slobodanTermin1.setCijenaTermina(150);
            slobodanTerminRepository.save(slobodanTermin1);
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
