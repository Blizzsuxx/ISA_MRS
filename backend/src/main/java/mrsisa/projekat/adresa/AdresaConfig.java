package mrsisa.projekat.adresa;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;


@Configuration
public class AdresaConfig {


    @Bean
    CommandLineRunner commandLineRunnerAdresa(AdresaRepository repozitorijum){
        return args ->{
            Adresa adresa =  new Adresa();
            adresa.setId(1L);
            adresa.setgDuzina(21.000);
            adresa.setgSirina(21.000);
            adresa.setPtt("broj");
            adresa.setMesto("Novi Sad");
            adresa.setUlica("Jevrejska");
            adresa.setBroj("2a");
            repozitorijum.save(adresa);
        };
    }
}
