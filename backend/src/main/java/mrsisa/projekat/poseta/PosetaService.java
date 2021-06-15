package mrsisa.projekat.poseta;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.farmaceut.FarmaceutRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.slobodanTermin.SlobodanTerminDTO;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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

    @Transactional
    public void izvrsiPregled(Long id, String opis){
        Poseta poseta = this.findId(id);
        poseta.setOpis(opis);
        poseta.setOtkazano(false);
        this.posetaRepository.save(poseta);
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

    @Transactional
    public List<PosetaDTO> dobaviPosete(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Radnik k = (Radnik)auth.getPrincipal();
        List<Poseta> posete = this.posetaRepository.findByRadnikProslo(k.getId());
        ArrayList<PosetaDTO> dto = new ArrayList<>();
        for(Poseta p : posete){
            dto.add(new PosetaDTO(p));
        }
        return dto;
    }

    @Transactional
    public List<PosetaDTO> dobaviPosetePacijenta(String id) {
        List<PosetaDTO> dto=new ArrayList<PosetaDTO>();
        List<Poseta> svePosete=this.posetaRepository.findAllByPacijentId(Integer.parseInt(id));
        Dermatolog isDerm=null;
        for(Poseta p : svePosete){
            isDerm=this.dermatologRepository.findByIdD(p.getRadnik().getId());
            if(!p.getOtkazano() && p.getPocetak().isAfter(LocalDateTime.now()) && isDerm!=null ){
                dto.add(new PosetaDTO(p.getId(), p.getRadnik(), p.getPocetak(), p.getKraj(),  p.getApoteka()));
            }
        }
        return dto;
        /*
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

        return dto;*/
    }
    @Transactional
    public List<PosetaDTO> dobaviPosetePacijentaF(String id) {
        List<PosetaDTO> dto=new ArrayList<PosetaDTO>();
        List<Poseta> svePosete=this.posetaRepository.findAllByPacijentId(Integer.parseInt(id));
        Farmaceut isFarm=null;
        for(Poseta p : svePosete){
            isFarm=this.farmaceutRepository.findByIdD(p.getRadnik().getId());

            if(!p.getOtkazano() && p.getPocetak().isAfter(LocalDateTime.now()) && isFarm!=null ){
                dto.add(new PosetaDTO(p.getId(), p.getRadnik(), p.getPocetak(), p.getKraj(),  p.getApoteka()));
            }
        }
        return dto;


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
        return dto;*/
    }

    public Korisnik getTrenutnogKorisnika(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return k;
    }

    @Transactional
    public List<PosetaDTO> dobaviIstorijuD() {
        Korisnik k=getTrenutnogKorisnika();
        List<Poseta> istorija=this.posetaRepository.findAllByPacijentId(k.getId());
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
        Korisnik k=getTrenutnogKorisnika();
        List<Poseta> istorija=this.posetaRepository.findAllByPacijentId(k.getId());

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
    @Transactional
    public List<SlobodanTerminDTO> slobodnePoseteD() {
        List<SlobodanTermin> sviTermini=this.slobodanTerminRepository.findAll();

        List<SlobodanTerminDTO> zaSlanje=new ArrayList<>();
        for(SlobodanTermin p : sviTermini){

                Dermatolog r=this.dermatologRepository.findByIdD(p.getRadnik().getId());
                Apoteka a=this.apotekaRepository.findOneById(p.getApoteka().getId());
                if(r!=null && a!=null){

                zaSlanje.add(new SlobodanTerminDTO(p,r,a));}

        }
        return zaSlanje;

    }

    @Transactional(propagation =Propagation.REQUIRES_NEW) //todo  isto kao slobodanTerminService fja zakazi
    public String zakaziPosetuD(String id) {
        String broj=id.split("=")[0];
        SlobodanTermin termin=this.slobodanTerminRepository.findOneById(Long.parseLong(broj.trim()));
        Poseta p=new Poseta();
        p.setRadnik(termin.getRadnik());//dovoljno da poseta ima pacijenta
        p.setKraj(termin.getKrajTermina());
        p.setPocetak(termin.getPocetakTermina());
        List<Poseta> sve=this.posetaRepository.findAll();

        p.setOtkazano(false);
        p.setApoteka(termin.getApoteka());
        Korisnik k=getTrenutnogKorisnika();
        Pacijent pacijent=this.pacijentRepository.findOneById(k.getId());
        p.setPacijent(pacijent);
        //this.posetaRepository.save(p); //todo mozda pacijentu setovati i cuvati?
        sacuvajPosetu(p);
        deleteTermin(termin.getId());
        return "Uspesno ste zakazali posetu kod dermatologa";
    }

    private Long pronadjiKljuc(List<Poseta> sve) {
        Long kljuc=1L;
        for(Poseta posete : sve){
            if(posete.getId()>kljuc){
                kljuc=posete.getId();
            }
        }
        kljuc++;
        System.out.println("Kljuc je: "+kljuc);
        return kljuc;
    }


    @Transactional //(readOnly=false) todo //ovde je cuvanje posete, e sad, treba onemoguciti da se ovo desi 2 pputa
    public void sacuvajPosetu(Poseta poseta){//kada se upisuje ide pessimistic //pazi na slobodan termin da se yakljuca nekako
        logger.info("> create");
        Poseta sacuvana=posetaRepository.save(poseta);
        logger.info("< create");

    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//
    public void deleteTermin(long id) {
        System.out.println("doslo delete");
        logger.info("> delete");
        slobodanTerminRepository.deleteById(id);
        logger.info("< delete");
    }

    @Transactional
    public boolean izbaciPosetuF(String id) {

        for(Poseta poseta : this.posetaRepository.findAll()){
            if(poseta.getId()==Integer.parseInt(id.trim())){
                List<SlobodanTermin> termini=this.slobodanTerminRepository.findAll();
                SlobodanTermin sl=new SlobodanTermin(poseta);//proveriti generisanje id
                sl.setApoteka(poseta.getApoteka());
                this.slobodanTerminRepository.save(sl);
                this.posetaRepository.delete(poseta);
                //sacuvati slobodan termin u bazu //sacuvati sve posete sem ove u bazu i to je to
                break;
            }
        }


        return true;
    }


    private Long izracunajKljuc(List<SlobodanTermin> termini) {
        Long kljuc=1L;
        for(SlobodanTermin posete : termini){
            if(posete.getId()>kljuc){
                kljuc=posete.getId();
            }
        }
        kljuc++;
        System.out.println("Kljuc je: "+kljuc);
        return kljuc;

    }


    @Transactional
    public void zabeleziOdsustvo(Long id) {
        Poseta poseta = this.posetaRepository.findById(id).orElse(null);
        poseta.setOtkazano(true);
    }

    @Transactional(readOnly=true)
    public List<PosetaDTO> getAktivnePosete(Radnik radnik) {



        List<Poseta> posete = this.posetaRepository.findByRadnikAktivno(radnik.getId());
        ArrayList<PosetaDTO> dtoPosete = new ArrayList<>();
        for(Poseta a : posete){
            dtoPosete.add(new PosetaDTO(a));
            System.out.println(a.getId());
        }

        return dtoPosete;

    }

    @Transactional
    public Boolean proveriDostupnost(Map<String, Object> params, StanjeLijekaRepository stanjeLijekaRepository) {
        Poseta poseta = this.findId(Long.parseLong(params.get("pregledID").toString()));

        List<Map<String, Object>> lekoviID = (List<Map<String, Object>>) params.get("lijekovi");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(params.get("lijekovi"));
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for(Map<String, Object> token : lekoviID){
            Map<String, Object> lek = (Map<String, Object>) token.get("lijek");
            boolean lekPostojiUApoteci = false;
            for(StanjeLijeka stanjeLijeka : poseta.getApoteka().getLijekovi()){
                if(stanjeLijeka.getId().equals (Long.parseLong(lek.get("id").toString()))){
                    lekPostojiUApoteci = true;
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.println(stanjeLijeka.getKolicina());
                    System.out.println(lek.get("kolicina"));
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    if(stanjeLijeka.getKolicina() < Long.parseLong(lek.get("kolicina").toString())){
                        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

                        return true;
                    }
                    else{
                        stanjeLijeka.setZatrazen(stanjeLijeka.getZatrazen()+1);
                        stanjeLijeka.setZatrazenDatum(LocalDateTime.now());
                        stanjeLijekaRepository.save(stanjeLijeka);
                    }
                    break;
                }
            }
            if(!lekPostojiUApoteci){
                return true;
            }
        }

        return false;
    }
}
