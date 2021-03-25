package mrsisa.projekat.apoteka;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekaService {
    public List<StanjeLijeka> dobaviStanjaLijekova() {

        Apoteka apoteka =  new Apoteka();
        apoteka.setId(1L);
        apoteka.setIme("Prva apoteka");
        List<StanjeLijeka> stanjaLijekova = List.of(
                new StanjeLijeka(
                        1L,
                        new Lijek(
                                1L,
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
                        2L,
                        new Lijek(
                                2L,
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
                        3L,
                        new Lijek(
                                3L,
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
    			new Apoteka(1L,"Apoteka1",null),new Apoteka(2L,"Apoteka2",null),new Apoteka(3L,"Apoteka3",null),new Apoteka(4L,"Apoteka4",null));
    	Adresa ad=new Adresa("Kazahstan","ptt","Ulica",5,50, 50);
    	for(Apoteka a : apoteke) {
    		a.setAdresa(ad);
    	}
    	return apoteke;
    }
}
