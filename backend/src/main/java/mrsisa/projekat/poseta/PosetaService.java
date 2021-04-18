package mrsisa.projekat.poseta;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosetaService {
    private final PosetaRepository posetaRepository;

    @Autowired
    public PosetaService(PosetaRepository apotekaRepository){
        this.posetaRepository = apotekaRepository;
    }

    public List<Poseta> dobaviPosete(Long id) {
        Apoteka apoteka = null;
        if(apoteka== null)
            return new ArrayList<Poseta>();
        return null;
    }
    public List<Poseta> dobaviPosete(){

        Farmaceut f = new Farmaceut();
        f.setFirstName("Pera");
        f.setLastName("Peric");

        Dermatolog d = new Dermatolog();
        d.setFirstName("John");
        d.setLastName("Titor");
        LocalDateTime d1 = LocalDateTime.of(2015, 5, 11, 5, 45);
        LocalDateTime d2 = LocalDateTime.of(2015, 5, 11, 6, 45);
        Apoteka a = new Apoteka();
        a.setIme("Poteka");
        Adresa adresa = new Adresa();
        adresa.setUlica("Faze 21");
        adresa.setMesto("Pozarevac");
        a.setAdresa(adresa);
        Pacijent pac = new Pacijent();
        pac.setFirstName("Dragan");
        pac.setLastName("Arsic");


        Poseta p1 = new Poseta((long)1, pac, f, d1, d2, a, new ArrayList<Erecept>());
        Poseta p2 = new Poseta((long)1, pac, d, d1, d2, a, new ArrayList<Erecept>());
        return List.of(p1, p2);
    }

}
