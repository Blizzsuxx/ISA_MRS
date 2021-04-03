package mrsisa.projekat.stanjelijeka;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Configuration
public class StanjeLijekaConfig {

    @Bean
    CommandLineRunner commandLineRunner1(StanjeLijekaRepository repozitorijum, LijekRepository lijekovi,
                                         ApotekaRepository apotekaRepository){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return args ->{
            repozitorijum.save(new StanjeLijeka(
                    1L,
                    lijekovi.findById(1L).orElse(null),
                    10,
                    false,
                    10.00,
                    LocalDateTime.parse("01-04-2021 10:10",format),
                    apotekaRepository.findById(1L).orElse(null)
            ));
            repozitorijum.save(new StanjeLijeka(
                    2L,
                    lijekovi.findById(2L).orElse(null),
                    10,
                    false,
                    10.00,
                    LocalDateTime.parse("01-04-2021 10:10",format),
                    apotekaRepository.findById(1L).orElse(null)
            ));
            repozitorijum.save(new StanjeLijeka(
                    3L,
                    lijekovi.findById(3L).orElse(null),
                    10,
                    false,
                    10.00,
                    LocalDateTime.parse("01-04-2021 10:10",format),
                    apotekaRepository.findById(1L).orElse(null)
            ));
        };
    }
}
