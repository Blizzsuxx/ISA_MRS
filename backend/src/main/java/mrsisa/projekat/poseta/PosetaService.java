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
import java.util.Map;

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

    public void kreirajPosetu(Map<String, Object> podaci){
        System.out.println(podaci.get("korisnik"));
    }
    public List<Poseta> dobaviPosete(){

        Farmaceut f = new Farmaceut();
        f.setFirstName("Pera");
        f.setLastName("Peric");

        Dermatolog d = new Dermatolog();
        d.setFirstName("John");
        d.setLastName("Titor");
        LocalDateTime d1 = LocalDateTime.of(2021, 5, 11, 5, 45);
        LocalDateTime d2 = LocalDateTime.of(2021, 5, 11, 6, 45);
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


    public List<Poseta> dobaviPosetePacijenta(String id) {

        Poseta poseta1=new Poseta();
        Apoteka apoteka1=new Apoteka();apoteka1.setIme("apo1");
        poseta1.setApoteka(apoteka1);
        Pacijent zarko=new Pacijent(); zarko.setFirstName("zarko");

        Dermatolog derm=new Dermatolog(); derm.setFirstName("micko");derm.setLastName("C");
        poseta1.setPacijent(zarko);
        poseta1.setRadnik(derm);
        poseta1.setPocetak(LocalDateTime.now());
        poseta1.setKraj(LocalDateTime.now().plusMinutes(30));
        poseta1.setId(1L);

        Poseta poseta2=new Poseta();
        Apoteka apoteka2=new Apoteka();apoteka2.setIme("apo2");
        poseta2.setApoteka(apoteka2);
        poseta2.setPacijent(zarko);
        Dermatolog derm2=new Dermatolog(); derm2.setFirstName("micko2");derm2.setLastName("C");
        poseta2.setRadnik(derm2);
        poseta2.setPocetak(LocalDateTime.now().plusMinutes(50));
        poseta2.setKraj(LocalDateTime.now().plusMinutes(70));
        poseta2.setId(2L);

        Poseta poseta3=new Poseta();
        Apoteka apoteka3=new Apoteka();apoteka3.setIme("apo3");
        poseta3.setApoteka(apoteka3);


        poseta3.setPacijent(zarko);
        poseta3.setRadnik(derm);
        poseta3.setPocetak(LocalDateTime.now().plusDays(1));
        poseta3.setKraj(LocalDateTime.now().plusDays(1).plusMinutes(30));
        poseta3.setId(3L);
        ArrayList<Poseta> posete=new ArrayList<>();posete.add(poseta1);posete.add(poseta2);posete.add(poseta3);
        return posete;
    }
    public List<Poseta> dobaviPosetePacijentaF(String id) {

        Poseta poseta1=new Poseta();
        Apoteka apoteka1=new Apoteka();apoteka1.setIme("apo1");
        poseta1.setApoteka(apoteka1);
        Pacijent zarko=new Pacijent(); zarko.setFirstName("zarko");

        Farmaceut derm=new Farmaceut(); derm.setFirstName("micko");derm.setLastName("C");
        poseta1.setPacijent(zarko);
        poseta1.setRadnik(derm);
        poseta1.setPocetak(LocalDateTime.now());
        poseta1.setKraj(LocalDateTime.now().plusMinutes(30));
        poseta1.setId(1L);

        Poseta poseta2=new Poseta();
        Apoteka apoteka2=new Apoteka();apoteka2.setIme("apo2");
        poseta2.setApoteka(apoteka2);
        poseta2.setPacijent(zarko);
        Farmaceut derm2=new Farmaceut(); derm2.setFirstName("micko2");derm2.setLastName("C");
        poseta2.setRadnik(derm2);
        poseta2.setPocetak(LocalDateTime.now().plusMinutes(50));
        poseta2.setKraj(LocalDateTime.now().plusMinutes(70));
        poseta2.setId(2L);

        Poseta poseta3=new Poseta();
        Apoteka apoteka3=new Apoteka();apoteka3.setIme("apo3");
        poseta3.setApoteka(apoteka3);


        poseta3.setPacijent(zarko);
        poseta3.setRadnik(derm);
        poseta3.setPocetak(LocalDateTime.now().plusDays(1));
        poseta3.setKraj(LocalDateTime.now().plusDays(1).plusMinutes(30));
        poseta3.setId(3L);
        ArrayList<Poseta> posete=new ArrayList<>();posete.add(poseta1);posete.add(poseta2);posete.add(poseta3);
        return posete;
    }

    public List<Poseta> dobaviIstorijuD() {
        Poseta poseta1=new Poseta();
        Apoteka apoteka1=new Apoteka();apoteka1.setIme("apo1");
        poseta1.setApoteka(apoteka1);
        Pacijent zarko=new Pacijent(); zarko.setFirstName("zarko");

        Dermatolog derm=new Dermatolog(); derm.setFirstName("micko");derm.setLastName("C");
        poseta1.setPacijent(zarko);
        poseta1.setRadnik(derm);
        poseta1.setPocetak(LocalDateTime.now());
        poseta1.setKraj(LocalDateTime.now().plusMinutes(30));
        poseta1.setId(1L);

        Poseta poseta2=new Poseta();
        Apoteka apoteka2=new Apoteka();apoteka2.setIme("apo2");
        poseta2.setApoteka(apoteka2);
        poseta2.setPacijent(zarko);
        Dermatolog derm2=new Dermatolog(); derm2.setFirstName("micko2");derm2.setLastName("C");
        poseta2.setRadnik(derm2);
        poseta2.setPocetak(LocalDateTime.now().plusMinutes(50));
        poseta2.setKraj(LocalDateTime.now().plusMinutes(70));
        poseta2.setId(2L);

        Poseta poseta3=new Poseta();
        Apoteka apoteka3=new Apoteka();apoteka3.setIme("apo3");
        poseta3.setApoteka(apoteka3);


        poseta3.setPacijent(zarko);
        poseta3.setRadnik(derm);
        poseta3.setPocetak(LocalDateTime.now().plusDays(1));
        poseta3.setKraj(LocalDateTime.now().plusDays(1).plusMinutes(30));
        poseta3.setId(3L);
        ArrayList<Poseta> posete=new ArrayList<>();posete.add(poseta1);posete.add(poseta2);posete.add(poseta3);
        return posete;
    }
}
