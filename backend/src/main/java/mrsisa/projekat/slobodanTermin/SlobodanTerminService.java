package mrsisa.projekat.slobodanTermin;


import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.DermatologRepository;
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
    public SlobodanTerminService(SlobodanTerminRepository slobodanTerminRepository,ApotekaRepository apotekaRepository,
                                 DermatologRepository dermatologRepository){
        this.slobodanTerminRepository = slobodanTerminRepository;
        this.apotekaRepository = apotekaRepository;
        this.dermatologRepository = dermatologRepository;
    }

    public List<SlobodanTerminDTO> dobaviSlobodneTermineDermatologa(Integer id) {

        List<SlobodanTerminDTO> termini =  new ArrayList<>();
        for(SlobodanTermin termin: this.slobodanTerminRepository.findAll()){

            if(termin.getDermatolog().getId().equals(id)){
                termini.add(new SlobodanTerminDTO(termin));
            }
        }
        return termini;
    }

    public void kreirajNoviTermin(SlobodanTerminDTO dto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        SlobodanTermin novi =  new SlobodanTermin();
        novi.setDermatolog(dermatologRepository.findById(dto.getId().intValue()).orElse(null));
        novi.setApoteka(apotekaRepository.findById(1L).orElse(null));
        novi.setCijenaTermina(dto.getCijenaTermina());
        novi.setPocetakTermina(LocalTime.parse(dto.getPocetakTermina(),dtf));
        novi.setKrajTermina(LocalTime.parse(dto.getKrajTermina(),dtf));
        this.slobodanTerminRepository.save(novi);

    }
}
