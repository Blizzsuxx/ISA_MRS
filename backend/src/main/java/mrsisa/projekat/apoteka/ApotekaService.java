package mrsisa.projekat.apoteka;


import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.StanjeLijeka;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekaService {
    public List<StanjeLijeka> dobaviStanjaLijekova() {

        Apoteka apoteka =  new Apoteka();
        apoteka.setId(1);
        apoteka.setIme("Prva apoteka");
        List<StanjeLijeka> stanjaLijekova = List.of(
                new StanjeLijeka(
                        1,
                        new Lijek(
                                1,
                                "Paracetamol",
                                "Protiv boli",
                                "tableta",
                                "ljiek",
                                "Biofarm",
                                "Lijek"
                        ),
                        10,
                        false
                ),
                new StanjeLijeka(
                        2,
                        new Lijek(
                                2,
                                "Brufen",
                                "Protiv boli",
                                "tableta",
                                "ljiek",
                                "Biofarm",
                                "Lijek"
                        ),
                        20,
                        false
                ),
                new StanjeLijeka(
                        3,
                        new Lijek(
                                3,
                                "Lekadol",
                                "Protiv boli",
                                "tableta",
                                "ljiek",
                                "Biofarm",
                                "Lijek"
                        ),
                        15,
                        true
                )
        );
        apoteka.setLijekovi(stanjaLijekova);
        return apoteka.getLijekovi();
    }
}
