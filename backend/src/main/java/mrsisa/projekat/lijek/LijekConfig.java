package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Configuration
public class LijekConfig {
    @Bean
    CommandLineRunner commandLineRunner2(LijekRepository repozitorijum){

        return args ->{
            repozitorijum.save(new Lijek(
                    1L,
                    "Paracetamol",
                    "Protiv boli",
                    "tableta",
                    "ljiek",
                    "Biofarm",
                    "Lijek"
            ));
            repozitorijum.save(new Lijek(
                    2L,
                    "Brufen",
                    "Protiv boli",
                    "tableta",
                    "ljiek",
                    "Biofarm",
                    "Lijek"
            ));
            repozitorijum.save(new Lijek(
                    3L,
                    "Lekadol",
                    "Protiv boli",
                    "tableta",
                    "ljiek",
                    "Biofarm",
                    "Lijek"
            ));

        };
    }
}
