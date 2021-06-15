package mrsisa.projekat.slobodanTermin;


import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.farmaceut.FarmaceutRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.popust.Popust;
import mrsisa.projekat.popust.PopustRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.radnik.RadnikRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import javax.transaction.Transactional;
import java.io.SyncFailedException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional//(readOnly=true)
public class SlobodanTerminService {
    final private SlobodanTerminRepository slobodanTerminRepository;
    final private ApotekaRepository apotekaRepository;
    final private DermatologRepository dermatologRepository;
    final private RadnikRepository radnikRepository;
    final private PosetaRepository posetaRepository;
    final private PacijentRepository pacijentRepository;
    final private PopustRepository popustRepository;
    final private FarmaceutRepository farmaceutRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SlobodanTerminService(SlobodanTerminRepository slobodanTerminRepository,ApotekaRepository apotekaRepository,
                                 FarmaceutRepository farmaceutRepository,
                                 DermatologRepository dermatologRepository, RadnikRepository radnikRep, PopustRepository popustRepository,
                                 PosetaRepository posetaRepository, PacijentRepository pacijentRepository){
        this.slobodanTerminRepository = slobodanTerminRepository;
        this.apotekaRepository = apotekaRepository;
        this.dermatologRepository = dermatologRepository;
        this.radnikRepository=radnikRep;
        this.posetaRepository=posetaRepository;
        this.pacijentRepository=pacijentRepository;
        this.popustRepository=popustRepository;
        this.farmaceutRepository=farmaceutRepository;
    }

    public List<SlobodanTerminDTO> dobaviSlobodneTermineDermatologa(Integer id) {

        List<SlobodanTerminDTO> termini =  new ArrayList<>();
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){

            if(termin.getRadnik().getId().equals(id)){
                termini.add(new SlobodanTerminDTO(termin));
            }
        }
        return termini;
    }

    public void kreirajNoviTermin(SlobodanTerminDTO dto) {
        System.out.println(dto.getPocetakTermina());
        System.out.println(dto.getKrajTermina());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        SlobodanTermin novi =  new SlobodanTermin();
        novi.setRadnik(radnikRepository.findById(dto.getId().intValue()).orElse(null));
        novi.setApoteka(apotekaRepository.findById(1L).orElse(null));
        novi.setCijenaTermina(dto.getCijenaTermina());
        novi.setPocetakTermina(LocalDateTime.parse(dto.getPocetakTermina(),dtf));
        novi.setKrajTermina(LocalDateTime.parse(dto.getKrajTermina(),dtf));
        this.slobodanTerminRepository.save(novi);

    }

    public List<SlobodanTerminDTO> dobaviSlobodneTermineApoteka(Long id) {
        List<SlobodanTerminDTO> termini =  new ArrayList<>();
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){

            if(termin.getApoteka().getId().equals(id)){
                termini.add(new SlobodanTerminDTO(termin));
            }
        }
        return termini;
    }
   @Transactional
    public List<SlobodanTerminDTO> dobaviSlobodneTermine(String dan) {
        //May 11 2021 08 40 08 40 (Jun, Jul) //postoji mogucnost da se ovaj deo menja, jer slobodni termini nemaju apoteke, pa bi onda prolazila kroz listu apoteka i njihove liste termina :p
       HashMap<String,Integer> daniMeseca=new HashMap<String,Integer>();
       daniMeseca.put("May",5);daniMeseca.put("Jun",6);daniMeseca.put("Jul",7); daniMeseca.put("Aug",8);daniMeseca.put("Sep",9);daniMeseca.put("Oct",10);
       String[] brojevi=dan.split(" ");
       LocalDateTime danPocetka=LocalDateTime.of(Integer.parseInt(brojevi[2].trim()),daniMeseca.get(brojevi[0]),
               Integer.parseInt(brojevi[1].trim()),Integer.parseInt(brojevi[3].trim()),Integer.parseInt(brojevi[4].trim()));
       LocalDateTime danKraja=LocalDateTime.of(Integer.parseInt(brojevi[2].trim()),daniMeseca.get(brojevi[0]),
               Integer.parseInt(brojevi[1].trim()),Integer.parseInt(brojevi[5].trim()),Integer.parseInt(brojevi[6].trim()));

        System.out.println(danPocetka);
        System.out.println(danKraja);
       List<SlobodanTerminDTO> termini =  new ArrayList<>();
       HashMap<Long, SlobodanTerminDTO> mapaTermina=new HashMap<>();
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){
            if((termin.getPocetakTermina().isAfter(danPocetka) || termin.getPocetakTermina().isEqual(danPocetka)) &&
                    (termin.getKrajTermina().isBefore(danKraja) || termin.getKrajTermina().isEqual(danKraja))
            ){
                Farmaceut r=this.farmaceutRepository.findByIdD(termin.getRadnik().getId());
                if(r!=null){
                mapaTermina.put(termin.getId(),new SlobodanTerminDTO(termin));
                //termini.add(new SlobodanTerminDTO(termin));
                ApotekaDTO a=new ApotekaDTO(termin.getApoteka(), termin.getCijenaTermina() );
                mapaTermina.get(termin.getId()).setApoteka(a);
                mapaTermina.get(termin.getId()).setImeRadnika(termin.getRadnik().getFirstName());
                mapaTermina.get(termin.getId()).setPrezimeRadnika(termin.getRadnik().getLastName());
                mapaTermina.get(termin.getId()).setOcenaRadnika(izracunajOcenu((r.getOcene())));}
                //termini.get(termini.size()-1).setApoteka(a);
                //termini.get(termini.size()-1).setImeRadnika(termin.getRadnik().getFirstName());
                //termini.get(termini.size()-1).setImeRadnika(termin.getRadnik().getFirstName());
                //termini.get(termini.size()-1).setImeRadnika(termin.getRadnik().getFirstName());
                //termini.get(termini.size()-1).setOcenaRadnika(5);
            }
        }
        for(SlobodanTerminDTO sl : mapaTermina.values()){
            termini.add(sl);
        }

        return termini;
    }

    private int izracunajOcenu(List<Ocena> sveOceneApoteke) {
        double ocena=0;
        for(Ocena o : sveOceneApoteke) {
            if (o != null) {
                ocena += o.getOcena();
            }
        }
        if(sveOceneApoteke.size()!=0){
            ocena=ocena/sveOceneApoteke.size();}
        return (int)ocena;

    }

    @Transactional//(propagation =Propagation.REQUIRES_NEW) //ovde isto dode oznaka
    public void zakazi(Object dto) {
        System.out.println(dto);
        System.out.println(dto);
        String deo=dto+"";
        String idZagrada=deo.split("=")[1];
        String id=idZagrada.replace("}", "");

        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){
            if(Integer.parseInt(id.trim())==termin.getId()){
                //System.out.println("gggg");
                List<Poseta> sve=this.posetaRepository.findAll();System.out.println("gggg");
                Poseta p = new Poseta( );//proveri kako da generises id , proveri kako da dobijes pacijenta
                p.setRadnik(termin.getRadnik());//dovoljno da poseta ima pacijenta
                p.setKraj(termin.getKrajTermina());
                p.setPocetak(termin.getPocetakTermina());


                Korisnik k=getTrenutnogKorisnika();
                Pacijent pac=this.pacijentRepository.findOneById(k.getId());
                p.setPacijent(pac);
                p.setOtkazano(false);
                System.out.println(p.getOtkazano()+"lana ");
                p.setApoteka(termin.getApoteka()); //ovde posle dode save
                System.out.println("uspeh");

                p.setCena(termin.getCijenaTermina());
                Popust popust=this.popustRepository.findById(1);//todo proveri da li samo 1 postoji
                System.out.println("lonly, just");
                if(pac.getBrojPoena()!=0){
                if(pac.getBrojPoena()<=popust.getDoRegular()){
                    p.setCena(p.getCena()*(1-((double)popust.getPopustRegular()/100)));}
                else if(pac.getBrojPoena()<=popust.getDoSilver() && pac.getBrojPoena()>popust.getDoRegular()){
                    p.setCena(p.getCena()*(1-((double)popust.getPopustSilver()/100)));}
                else {
                    p.setCena(p.getCena()*(1-((double)popust.getPopustGold()/100)));
                }} //todo sacuvaj i u posetiocu i u radniku?
                //todo proveri sta treba sve da se sacuva, i proveri ko ne sme da ima istovremeno pristup bazi i za citanje i za pisanje
                //this.posetaRepository.save(p);
                sacuvajPosetu(p);
                deleteTermin(termin.getId());//todo da li jos negde treba da obrisem ili se automatski brise svuda
                break;
            }
        }

    }

    @Transactional//(readOnly=false) todo //ovde je cuvanje posete, e sad, treba onemoguciti da se ovo desi 2 pputa
    public void sacuvajPosetu(Poseta poseta){//kada se upisuje ide pessimistic //pazi na slobodan termin da se yakljuca nekako
        logger.info("> create");
        Poseta sacuvana=posetaRepository.save(poseta);
        logger.info("< create");

    }
    @Transactional//(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void deleteTermin(long id) {

        logger.info("> delete");
        slobodanTerminRepository.deleteById(id);
        logger.info("< delete");
    }


    public Korisnik getTrenutnogKorisnika(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return k;
    }
}
