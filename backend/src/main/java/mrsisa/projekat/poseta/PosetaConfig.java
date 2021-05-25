package mrsisa.projekat.poseta;


import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.erecept.EreceptRepository;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.radnik.RadnikRepository;
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
public class PosetaConfig {

    @Bean
    @Transactional
    CommandLineRunner commandLineRunnerPoseta(PosetaRepository repozitorijum, ApotekaRepository apotekaRepository,
                                              RadnikRepository radnikRepository, PacijentRepository pacijenti, EreceptRepository erecepti){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            Poseta p = new Poseta();
            p.setPocetak(LocalDateTime.now());
            p.setKraj(LocalDateTime.now().plusMinutes(30));
            p.setApoteka(apotekaRepository.findOneById(1));
            p.setErecepti(erecepti.findAll());
            p.setPacijent(pacijenti.findOneByUsername("zarko"));
            p.setRadnik(radnikRepository.getOne(5));
            repozitorijum.save(p);

            Poseta p2 = new Poseta();
            p2.setPocetak(LocalDateTime.of(2021, 5, 11, 5, 45));
            p2.setKraj(LocalDateTime.of(2021, 7, 11, 6, 45));
            p2.setApoteka(apotekaRepository.findOneById(1));
            p2.setErecepti(erecepti.findAll());
            p2.setPacijent(pacijenti.findOneByUsername("zarko"));
            p2.setRadnik(radnikRepository.getOne(5));
            repozitorijum.save(p2);

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
