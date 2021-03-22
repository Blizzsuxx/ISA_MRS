package mrsisa.projekat.apoteka;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.StanjeLijeka;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Apoteka> dobaviApoteke(){
    	//ovde treba ucitati apoteke
    	List<Apoteka> apoteke=List.of(
    			new Apoteka(1,"Apoteka1",null),new Apoteka(2,"Apoteka2",null),new Apoteka(3,"Apoteka3",null),new Apoteka(4,"Apoteka4",null));
    	Adresa ad=new Adresa("Kazahstan","ptt","Ulica",5,50, 50);
    	for(Apoteka a : apoteke) {
    		a.setAdresa(ad);
    	}
    	return apoteke;
    }
}
