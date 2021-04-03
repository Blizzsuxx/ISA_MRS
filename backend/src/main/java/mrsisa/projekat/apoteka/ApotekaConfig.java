package mrsisa.projekat.apoteka;

import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaConfig;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class ApotekaConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApotekaRepository repozitorijum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            Apoteka apoteka =  new Apoteka();
            apoteka.setId(1L);
            apoteka.setIme("Prva apoteka");

            repozitorijum.save(apoteka);
        };
    }
}
