package mrsisa.projekat.apoteka;

import mrsisa.projekat.adresa.AdresaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class ApotekaConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApotekaRepository repozitorijum, AdresaRepository adresaRepository){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
//            Apoteka apoteka =  new Apoteka();
//            apoteka.setId(1L);
//            apoteka.setIme("Prva apoteka");
//            apoteka.setAdresa(adresaRepository.findById(1L).orElse(null));
//            repozitorijum.save(apoteka);
//
//            Apoteka apoteka1 =new Apoteka(2L, "Apoteka 2",null);
//            repozitorijum.save(apoteka1);
        };
    }
}
