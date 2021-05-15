package mrsisa.projekat.poseta;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
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

    public List<PosetaDTO> dobaviPosete(Long id) {
        Apoteka apoteka = null;
        if(apoteka== null)
            return new ArrayList<PosetaDTO>();
        return null;
    }

    public void kreirajPosetu(Map<String, Object> podaci){
        System.out.println(podaci.get("korisnik"));
    }
    public List<PosetaDTO> dobaviPosete(){

        Farmaceut f = new Farmaceut();
        f.setFirstName("Pera");
        f.setLastName("Peric");

        Dermatolog d = new Dermatolog();
        d.setFirstName("John");
        d.setLastName("Titor");
        LocalDateTime d1 = LocalDateTime.of(2021, 5, 11, 5, 45);
        LocalDateTime d2 = LocalDateTime.of(2021, 7, 11, 6, 45);
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
        return List.of(new PosetaDTO(p1), new PosetaDTO(p2));
    }


    public List<PosetaDTO> dobaviPosetePacijenta(String id) {

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
        ArrayList<PosetaDTO> dto=new ArrayList<>();dto.add(new PosetaDTO(poseta1));dto.add(new PosetaDTO(poseta2)); dto.add(new PosetaDTO(poseta3));

        return dto;
    }
    public List<PosetaDTO> dobaviPosetePacijentaF(String id) {

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
        ArrayList<PosetaDTO> dto=new ArrayList<>();dto.add(new PosetaDTO(poseta1));dto.add(new PosetaDTO(poseta2)); dto.add(new PosetaDTO(poseta3));
        return dto;
    }

    public List<PosetaDTO> dobaviIstorijuD() {
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
        ArrayList<PosetaDTO> dto=new ArrayList<>();dto.add(new PosetaDTO(poseta1));dto.add(new PosetaDTO(poseta2)); dto.add(new PosetaDTO(poseta3));

        List<Erecept> erecepti=napraviErecepte(zarko);
        dto.get(0).setPrepisaniLekoviLista(erecepti);
        dto.get(1).setPrepisaniLekovi(new ArrayList<>());
        dto.get(2).setPrepisaniLekoviLista(erecepti);
        return dto;
    }

    public List<PosetaDTO> dobaviIstorijuF() {
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
        ArrayList<PosetaDTO> dto=new ArrayList<>();dto.add(new PosetaDTO(poseta1));dto.add(new PosetaDTO(poseta2)); dto.add(new PosetaDTO(poseta3));

        List<Erecept> erecepti=napraviErecepte(zarko);
        dto.get(0).setPrepisaniLekoviLista(erecepti);
        dto.get(1).setPrepisaniLekovi(new ArrayList<>());
        dto.get(2).setPrepisaniLekoviLista(erecepti);
        return dto;
    }

    public List<Erecept> napraviErecepte(Pacijent p){//TODO obrisi ovu fju kad sredis bazu

        List<Lijek> lek=new ArrayList<>();
        Adresa a=new Adresa("mesto", "ptt", "ulica",  "45", 50,50);
        p.setAdresa(a);
        p.setEmail("pera@gmail.com");
        lek.add(new Lijek(
                1L,
                "Paracetamol",
                "Protiv bolova",
                "tableta",
                "ljiek",
                "Biofarm",
                "Lijek"
        ));
        lek.add(new Lijek(
                2L,
                "Brufen",
                "Protiv bolova",
                "tableta",
                "lek",
                "Biofarm",
                "Lek"
        ));
        lek.add(new Lijek(
                3L,
                "Buscopan",
                "Protiv bolova",
                "tableta",
                "lek",
                "Biofarm",
                "Lek"
        ));
        p.setAlergije(lek);


        StanjeLijeka s1=new StanjeLijeka(p.getAlergije().get(0),1,false);
        StanjeLijeka s2=new StanjeLijeka(p.getAlergije().get(1),2,false);
        List<StanjeLijeka> sl=new ArrayList<>();
        sl.add(s1);
        sl.add(s2);

        Erecept rec=new Erecept();
        rec.setPacijent(p);
        rec.setDatumIzdavanja(LocalDateTime.now());
        rec.setSifra("1");
        rec.setPrepisaniLijekovi(sl);

        Erecept rec1=new Erecept();
        rec1.setPacijent(p);
        rec1.setDatumIzdavanja(LocalDateTime.now());
        rec1.setSifra("1");
        rec1.setPrepisaniLijekovi(sl);

        List<Erecept> li=new ArrayList<>();
        li.add(rec);
        li.add(rec1);
        return li;

    }

    public List<PosetaDTO> slobodnePoseteD() {
        List<PosetaDTO> svePosete=new ArrayList<>();
        Dermatolog d1=new Dermatolog();d1.setFirstName("Milos");d1.setLastName("Milinkovic");
        Dermatolog d2=new Dermatolog();d2.setFirstName("Zoki");d2.setLastName("Lazarevic");
        LocalDateTime dan1=LocalDateTime.now();
        LocalDateTime dan2=LocalDateTime.now().plusMinutes(30);
        LocalDateTime dan12=LocalDateTime.now().plusDays(1);
        LocalDateTime dan22=LocalDateTime.now().plusDays(1).plusMinutes(30);
        Apoteka a1=new Apoteka(); a1.setIme("Zdravkovic apoteka");
        Apoteka a2=new Apoteka();a2.setIme("Tvoja apoteka");
        PosetaDTO p1=new PosetaDTO(1L,d1,dan1+"", dan2+"","",a1,4,1000);
        PosetaDTO p2=new PosetaDTO(2L,d2,dan1+"", dan2+"","",a2,5,1500);
        PosetaDTO p3=new PosetaDTO(3L,d1,dan12+"", dan22+"","",a1, 4,1000);
        svePosete.add(p1);
        svePosete.add(p2);
        svePosete.add(p3);
        return svePosete;


    }

    public String zakaziPosetuD(String id) {
        return "Uspesno ste zakazali posetu kod dermatologa Sime.";
    }
}
