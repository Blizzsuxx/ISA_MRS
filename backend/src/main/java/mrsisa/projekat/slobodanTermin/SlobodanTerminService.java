package mrsisa.projekat.slobodanTermin;


import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.radnik.RadnikRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.SyncFailedException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SlobodanTerminService {
    final private SlobodanTerminRepository slobodanTerminRepository;
    final private ApotekaRepository apotekaRepository;
    final private DermatologRepository dermatologRepository;
    final private RadnikRepository radnikRepository;
    public SlobodanTerminService(SlobodanTerminRepository slobodanTerminRepository,ApotekaRepository apotekaRepository,
                                 DermatologRepository dermatologRepository, RadnikRepository radnikRep){
        this.slobodanTerminRepository = slobodanTerminRepository;
        this.apotekaRepository = apotekaRepository;
        this.dermatologRepository = dermatologRepository;
        this.radnikRepository=radnikRep;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
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
       daniMeseca.put("May",5);daniMeseca.put("Jun",6);daniMeseca.put("Jul",7);
       String[] brojevi=dan.split(" ");
       LocalDateTime danPocetka=LocalDateTime.of(Integer.parseInt(brojevi[2].trim()),daniMeseca.get(brojevi[0]),
               Integer.parseInt(brojevi[1].trim()),Integer.parseInt(brojevi[3].trim()),Integer.parseInt(brojevi[4].trim()));
       LocalDateTime danKraja=LocalDateTime.of(Integer.parseInt(brojevi[2].trim()),daniMeseca.get(brojevi[0]),
               Integer.parseInt(brojevi[1].trim()),Integer.parseInt(brojevi[5].trim()),Integer.parseInt(brojevi[6].trim()));

        System.out.println(danPocetka);
        System.out.println(danKraja);
       List<SlobodanTerminDTO> termini =  new ArrayList<>();
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){
            //System.out.println(termin.getPocetakTermina());
            //System.out.println(termin.getKrajTermina());
            if((termin.getPocetakTermina().isAfter(danPocetka) || termin.getPocetakTermina().isEqual(danPocetka)) &&
                    (termin.getKrajTermina().isBefore(danKraja) || termin.getKrajTermina().isEqual(danKraja))
            ){
                termini.add(new SlobodanTerminDTO(termin));
                ApotekaDTO a=new ApotekaDTO("dr max", "Novi sad","1111", "Futoski put","2a");
                termini.get(termini.size()-1).setApoteka(a);
                termini.get(termini.size()-1).setImeRadnika(termin.getRadnik().getFirstName());
                termini.get(termini.size()-1).setPrezimeRadnika(termin.getRadnik().getLastName());
                termini.get(termini.size()-1).setImeRadnika(termin.getRadnik().getFirstName());
                termini.get(termini.size()-1).setOcenaRadnika(5);
            }
        }
        return termini;
    }

    public void zakazi(Object dto) {
        System.out.println(dto);
        System.out.println(dto);
        String deo=dto+"";
        String idZagrada=deo.split("=")[1];
        String id=idZagrada.replace("}", "");
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){
            if(Integer.parseInt(id.trim())==termin.getId()){
                Poseta p = new Poseta( );//proveri kako da generises id , proveri kako da dobijes pacijenta
                p.setRadnik(termin.getRadnik());//dovoljno da poseta ima pacijenta
                p.setKraj(termin.getKrajTermina());
                p.setPocetak(termin.getPocetakTermina());
                p.setId(1L);
                p.setApoteka(termin.getApoteka()); //ovde posle dode save
                System.out.println("uspeh");
                break;
            }
        }
    }
}
