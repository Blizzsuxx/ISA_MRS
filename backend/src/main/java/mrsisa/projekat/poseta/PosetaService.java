package mrsisa.projekat.poseta;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.farmaceut.FarmaceutRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.slobodanTermin.SlobodanTerminDTO;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PosetaService {
    private final PosetaRepository posetaRepository;
    private final FarmaceutRepository farmaceutRepository;
    private final DermatologRepository dermatologRepository;
    private final ApotekaRepository apotekaRepository;
    private final PacijentRepository pacijentRepository;
    private final SlobodanTerminRepository slobodanTerminRepository;

    @Autowired
    public PosetaService(PosetaRepository posRepository, FarmaceutRepository farm, DermatologRepository derm,
                         ApotekaRepository apotekaRepository, PacijentRepository pacijentR, SlobodanTerminRepository slobodanR)
    {
        this.posetaRepository = posRepository;
        this.farmaceutRepository=farm;
        this.dermatologRepository=derm;
        this.apotekaRepository=apotekaRepository;
        this.pacijentRepository=pacijentR;
        this.slobodanTerminRepository=slobodanR;
    }

    public List<PosetaDTO> dobaviPosete(Long id) {
        Apoteka apoteka = null;
        if(apoteka== null)
            return new ArrayList<PosetaDTO>();
        return null;
    }


    public Poseta findId(Long id){

        return this.posetaRepository.getOne(id);
    }

    @Transactional
    public void kreirajPosetu(Map<String, Object> podaci){
        Long pregledID = Long.parseLong( podaci.get("pregledID").toString());
        Poseta poseta = this.findId(pregledID);
        Pacijent pacijent = poseta.getPacijent();
        Apoteka apoteka = poseta.getApoteka();
        Radnik radnik = poseta.getRadnik();
        ArrayList<String> dateTime = (ArrayList<String>)podaci.get("datetime");
        Poseta novaPoseta = new Poseta();
        novaPoseta.setPacijent(pacijent);
        novaPoseta.setApoteka(apoteka);
        novaPoseta.setRadnik(radnik);
        novaPoseta.setPocetak(LocalDateTime.parse(dateTime.get(0).substring(0,23)));
        novaPoseta.setKraj(LocalDateTime.parse(dateTime.get(1).substring(0,23)));
        this.posetaRepository.save(novaPoseta);
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
        Poseta p2 = new Poseta((long)2, pac, d, d1, d2, a, new ArrayList<Erecept>());
        Poseta p3 = new Poseta((long)3, pac, d, d1, d2, a, new ArrayList<Erecept>());
        return List.of(new PosetaDTO(p1), new PosetaDTO(p2), new PosetaDTO(p3));
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
    @Transactional
    public List<PosetaDTO> dobaviIstorijuD() {
        List<Poseta> istorija=this.posetaRepository.findAllByPacijentId(9); //TODO 9
        List<PosetaDTO> zaSlanje=new ArrayList<>();
        List<Dermatolog> farm=this.dermatologRepository.findAll();
        for(Poseta p : istorija){
            if(p.getKraj().isBefore(LocalDateTime.now())){
                for(Dermatolog f :farm){
                    if(f.getId()==p.getRadnik().getId()){

                        if(p.getPacijent()!=null){

                        zaSlanje.add(new PosetaDTO(p));}
                    }
                }
            }
        }
        return zaSlanje;

    }
    @Transactional
    public List<PosetaDTO> dobaviIstorijuF() {

        List<Poseta> istorija=this.posetaRepository.findAllByPacijentId(9); //TODO 9

        List<PosetaDTO> zaSlanje=new ArrayList<>();
        List<Farmaceut> farm=this.farmaceutRepository.findAll();
        for(Poseta p : istorija){
            if(p.getKraj().isBefore(LocalDateTime.now())){
                for(Farmaceut f :farm){
                    if(f.getId()==p.getRadnik().getId()){
                        if(p.getPacijent()!=null){
                            System.out.println("---------------------------------------------------------");
                            System.out.println(p.getErecepti().size());
                            if(p.getErecepti().size()>0){
                            System.out.println(p.getErecepti().get(0).getPrepisaniLijekovi().get(0));}
                            System.out.println("---------------------------------------------------------");
                        zaSlanje.add(new PosetaDTO(p));}
                    }
                }
            }
        }
        return zaSlanje;
        /*
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
        return dto;*/
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

    public List<SlobodanTerminDTO> slobodnePoseteD() {
        List<SlobodanTermin> sviTermini=this.slobodanTerminRepository.findAll();

        List<SlobodanTerminDTO> zaSlanje=new ArrayList<>();
        for(SlobodanTermin p : sviTermini){
            //todo slobodan termin kad se yakaze izbaciti iz liste i sacuvati i ovde i kod farm
                Dermatolog r=this.dermatologRepository.findByIdD(p.getRadnik().getId());
                Apoteka a=this.apotekaRepository.findOneById(p.getApoteka().getId());
                if(r!=null && a!=null){

                zaSlanje.add(new SlobodanTerminDTO(p,r,a));}

        }
        return zaSlanje;

    }

    public String zakaziPosetuD(String id) {
        String broj=id.split("=")[0];
        SlobodanTermin termin=this.slobodanTerminRepository.findOneById(Long.parseLong(broj.trim()));
        Poseta p=new Poseta();
        p.setRadnik(termin.getRadnik());//dovoljno da poseta ima pacijenta
        p.setKraj(termin.getKrajTermina());
        p.setPocetak(termin.getPocetakTermina());
        p.setId(100L); //TODO id
        p.setApoteka(termin.getApoteka());
        Pacijent pacijent=this.pacijentRepository.findOneById(9);//todo 9
        p.setPacijent(pacijent);
        this.posetaRepository.save(p);

        return "Uspesno ste zakazali posetu kod dermatologa Sime.";
    }

    public boolean izbaciPosetuF(String id) {

        for(Poseta poseta : this.posetaRepository.findAll()){
            if(poseta.getId()==Integer.parseInt(id.trim())){
                SlobodanTermin sl=new SlobodanTermin(poseta, 1L);//proveriti generisanje id
                //sacuvati slobodan termin u bazu //sacuvati sve posete sem ove u bazu i to je to
                break;
            }
        }


        return true;
    }

    
    @Transactional
    public void zabeleziOdsustvo(Long id) {
        Poseta poseta = this.posetaRepository.findById(id).orElse(null);
        poseta.setOtkazano(true);
    }

    @Transactional(readOnly=true)
    public List<PosetaDTO> getAktivnePosete(Radnik radnik) {

        return dobaviPosete(); //hotfix

        /*
        List<Poseta> posete = this.posetaRepository.findByRadnikAktivno(radnik.getId());
        ArrayList<PosetaDTO> dtoPosete = new ArrayList<>();
        for(Poseta a : posete){
            dtoPosete.add(new PosetaDTO(a));
        }

        return dtoPosete;
        */
    }
}
