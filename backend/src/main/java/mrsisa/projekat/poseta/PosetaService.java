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
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.pacijent.PacijentService;
import mrsisa.projekat.popust.Popust;
import mrsisa.projekat.popust.PopustService;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.slobodanTermin.SlobodanTerminDTO;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class PosetaService {
    private final PosetaRepository posetaRepository;
    private final FarmaceutRepository farmaceutRepository;
    private final DermatologRepository dermatologRepository;
    private final ApotekaRepository apotekaRepository;
    private final PacijentRepository pacijentRepository;
    private final SlobodanTerminRepository slobodanTerminRepository;
    private final PacijentService pacijentService;
    private final PopustService popustService;

    @Autowired
    public PosetaService(PosetaRepository posRepository, FarmaceutRepository farm, DermatologRepository derm,
                         ApotekaRepository apotekaRepository, PacijentRepository pacijentR, SlobodanTerminRepository slobodanR, PacijentService pacijentService, PopustService popustRepository)
    {
        this.posetaRepository = posRepository;
        this.farmaceutRepository=farm;
        this.dermatologRepository=derm;
        this.apotekaRepository=apotekaRepository;
        this.pacijentRepository=pacijentR;
        this.slobodanTerminRepository=slobodanR;
        this.pacijentService = pacijentService;
        this.popustService = popustRepository;
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
        Popust popust = this.popustService.findById(1);
        Pacijent pacijent = poseta.getPacijent();
        if(poseta.getRadnik() instanceof Farmaceut){
            pacijent.setBrojPoena(pacijent.getBrojPoena() + popust.getBrojPoenaSavetovanja());
        } else {
            pacijent.setBrojPoena(pacijent.getBrojPoena() + popust.getBrojPoenaPregleda());

        }
        this.posetaRepository.save(poseta);
    }


    public Poseta findId(Long id){

        return this.posetaRepository.getOne(id);
    }

    @Transactional
    public Boolean kreirajPosetu(Map<String, Object> podaci){
        Long pregledID = Long.parseLong( podaci.get("pregledID").toString());
        Poseta poseta = this.findId(pregledID);
        Pacijent pacijent = poseta.getPacijent();
        Apoteka apoteka = poseta.getApoteka();
        Radnik radnik = poseta.getRadnik();
        ArrayList<String> dateTime = null;
        LocalDateTime pocetak = null;
        LocalDateTime kraj = null;
        if(podaci.get("datetime").toString().isEmpty()){
            System.out.println(podaci.get("slobodan termin"));
            SlobodanTermin sl = this.slobodanTerminRepository.findOneById(Long.parseLong(podaci.get("slobodan termin").toString()));
            pocetak = (sl.getPocetakTermina());
            kraj = (sl.getKrajTermina());

        } else {
            dateTime = (ArrayList<String>) podaci.get("datetime");
            pocetak = LocalDateTime.parse(dateTime.get(0).substring(0,23));
            kraj = LocalDateTime.parse(dateTime.get(1).substring(0,23));
        }


        List<Poseta> posete = this.posetaRepository.findByRadnikAktivno(radnik.getId());
        for(Poseta p : posete){
            if(pocetak.isBefore(p.getPocetak()) && !kraj.isBefore(p.getPocetak())){
                return true;
            }
            if(pocetak.isAfter(p.getPocetak()) && pocetak.isBefore(p.getKraj())){
                return true;
            }
            if(pocetak.isEqual(p.getPocetak())){
                return true;
            }
        }
        List<RadnoVrijeme> rvreme = apoteka.getRadnaVremena();
        for(RadnoVrijeme rv : rvreme){
            Radnik rad = rv.getDermatolog();
            if(rad == null) rad = rv.getFarmaceuet();
            if(rad.getId().equals(radnik.getId())){
                if(rv.getPocetakRadnogVremena().isBefore(pocetak) && rv.getKrajRadnogVremena().isAfter(kraj)){
                    break;
                } else {
                    return true;
                }
            }
        }

        Poseta novaPoseta = new Poseta();
        novaPoseta.setPacijent(pacijent);
        novaPoseta.setApoteka(apoteka);
        novaPoseta.setRadnik(radnik);
        novaPoseta.setOtkazano(null);

        novaPoseta.setPocetak(LocalDateTime.parse(dateTime.get(0).substring(0,23)));
        novaPoseta.setKraj(LocalDateTime.parse(dateTime.get(1).substring(0,23)));
        radnik.getPosete().add(novaPoseta);
        this.posetaRepository.save(novaPoseta);
        System.out.println(podaci.get("korisnik"));

        if(podaci.get("datetime").toString().isEmpty()){
            System.out.println(podaci.get("slobodan termin"));
            SlobodanTermin sl = this.slobodanTerminRepository.findOneById(Long.parseLong(podaci.get("slobodan termin").toString()));
            this.slobodanTerminRepository.delete(sl);

        }
        return  false;
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

    public String zakaziPosetuD(String id) {
        String broj=id.split("=")[0];
        SlobodanTermin termin=this.slobodanTerminRepository.findOneById(Long.parseLong(broj.trim()));
        Poseta p=new Poseta();
        p.setRadnik(termin.getRadnik());//dovoljno da poseta ima pacijenta
        p.setKraj(termin.getKrajTermina());
        p.setPocetak(termin.getPocetakTermina());
        List<Poseta> sve=this.posetaRepository.findAll();
        Long id2=sve.get(sve.size()-1).getId()+1L;
        p.setId(id2);
        p.setOtkazano(false);
        p.setApoteka(termin.getApoteka());
        Korisnik k=getTrenutnogKorisnika();
        Pacijent pacijent=this.pacijentRepository.findOneById(k.getId());
        p.setPacijent(pacijent);
        this.posetaRepository.save(p);

        return "Uspesno ste zakazali posetu kod dermatologa Sime.";
    }
    @Transactional
    public boolean izbaciPosetuF(String id) {

        for(Poseta poseta : this.posetaRepository.findAll()){
            if(poseta.getId()==Integer.parseInt(id.trim())){
                List<SlobodanTermin> termini=new ArrayList<>();
                Long id1;
                if(termini.size()==0){
                    id1=1L;
                }else{
                 id1=termini.get(termini.size()-1).getId()+1;}
                SlobodanTermin sl=new SlobodanTermin(poseta, id1);//proveriti generisanje id
                sl.setApoteka(poseta.getApoteka());
                this.slobodanTerminRepository.save(sl);
                this.posetaRepository.delete(poseta);
                //sacuvati slobodan termin u bazu //sacuvati sve posete sem ove u bazu i to je to
                break;
            }
        }


        return true;
    }

    
    @Transactional
    public void zabeleziOdsustvo(Long id) {
        Poseta poseta = this.posetaRepository.findById(id).orElse(null);




        if(poseta.getRadnik() instanceof Farmaceut) {
            this.pacijentService.nijePreuzeolek(Math.toIntExact(poseta.getPacijent().getId()), 2);
        } else{
            this.pacijentService.nijePreuzeolek(Math.toIntExact(poseta.getPacijent().getId()), 3);

        }
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
        Boolean greska = false;
        for(Map<String, Object> token : lekoviID){
            Map<String, Object> lek =  token;
            boolean lekPostojiUApoteci = false;
            for(StanjeLijeka stanjeLijeka : poseta.getApoteka().getLijekovi()){
                if(stanjeLijeka.getId().toString().equals((lek.get("id").toString()))){
                    lekPostojiUApoteci = true;
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.println(stanjeLijeka.getKolicina());
                    System.out.println(lek.get("kolicina"));
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    if(stanjeLijeka.getKolicina() < Long.parseLong(lek.get("kolicina").toString())){
                        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        stanjeLijeka.setZatrazen(stanjeLijeka.getZatrazen()+1);
                        stanjeLijeka.setZatrazenDatum(LocalDateTime.now());
                        stanjeLijekaRepository.save(stanjeLijeka);
                        greska = true;
                    }
                    else{

                    }
                    break;
                }
            }
            if(!lekPostojiUApoteci){
                greska = true;

            }
        }

        return greska;
    }


    @Transactional
    public Map<String, List<LijekDTO>> traziZamenu(Map<String, Object> params) {

        Poseta poseta = findId(Long.parseLong(params.get("pregledID").toString()));
        Map<String, List<LijekDTO>> lekoviZaPreporuku = new HashMap<>();
        List<Map<String, Object>> lekoviID = (List<Map<String, Object>>) params.get("lijekovi");
        System.out.println("ZAMENA");
        System.out.println("ZAMENA");
        System.out.println("ZAMENA");
        System.out.println("ZAMENA");
        System.out.println("ZAMENA");

        Apoteka apoteka = this.apotekaRepository.findOneById(poseta.getApoteka().getId());
        for(Map<String, Object> token : lekoviID){
            Map<String, Object> lek =  token;
            System.out.println("ZAMENA");
            System.out.println(lek);
            System.out.println("ZAMENA");

            for(StanjeLijeka stanjeLijeka : apoteka.getLijekovi()){
                if(stanjeLijeka.getId().toString().equals(lek.get("id").toString())){
                    if(stanjeLijeka.getKolicina() < (int)lek.get("kolicina")){
                        List<Lijek> lekList = stanjeLijeka.getLijek().getZamenskiLijekovi();
                        ArrayList<LijekDTO> dtoList = new ArrayList<>();
                        for(Lijek l : lekList){dtoList.add(new LijekDTO(l));}
                        lekoviZaPreporuku.put(stanjeLijeka.getLijek().getSifra(), dtoList);
                    }
                    else{

                    }
                    break;
                }
            }
        }
        System.out.println("AAAAAAA");
        System.out.println("AAAAAAA");

        for( List<LijekDTO> a : lekoviZaPreporuku.values()){
            System.out.println(a);
            for(LijekDTO b : a){
                System.out.println(b.getNaziv());
            }
        }
        System.out.println("AAAAAAA");
        System.out.println("AAAAAAA");

        return lekoviZaPreporuku;



    }

    @Transactional
    public List<SlobodanTerminDTO> slobodniTermini(Map<String, Object> data) {
        Long id = Long.parseLong(data.get("posetaId").toString());
        Poseta poseta = this.posetaRepository.findOneById(id);

        Long apotekaId = poseta.getApoteka().getId();
        Long radnikId = Long.valueOf(poseta.getRadnik().getId());
        List<SlobodanTermin> sviTermini=this.slobodanTerminRepository.findAll();

        List<SlobodanTerminDTO> zaSlanje=new ArrayList<>();
        for(SlobodanTermin p : sviTermini){

            Dermatolog r=this.dermatologRepository.findByIdD(p.getRadnik().getId());
            Apoteka a=this.apotekaRepository.findOneById(p.getApoteka().getId());

            if(r!=null && a!=null && apotekaId.toString().equals(a.getId().toString()) && radnikId.toString().equals(r.getId().toString())){

                zaSlanje.add(new SlobodanTerminDTO(p,r,a));}

        }
        return zaSlanje;
    }
}
