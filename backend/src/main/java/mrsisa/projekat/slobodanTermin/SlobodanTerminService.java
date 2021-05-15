package mrsisa.projekat.slobodanTermin;


import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.radnik.RadnikRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        novi.setPocetakTermina(LocalTime.parse(dto.getPocetakTermina(),dtf));
        novi.setKrajTermina(LocalTime.parse(dto.getKrajTermina(),dtf));
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
}
