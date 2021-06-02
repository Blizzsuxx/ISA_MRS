package mrsisa.projekat.rezervacija;

import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RezervacijaService {
    private final RezervacijaRepository repository;

    @Autowired
    public RezervacijaService(RezervacijaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void postaviRezervaciju(Map<String, Object> podaci, PosetaService posetaService) {
        Long pregledID = Long.parseLong(podaci.get("pregledID").toString());
        Poseta poseta = posetaService.findId(pregledID);
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatumRezervacije(LocalDateTime.now());
        rezervacija.setApoteka(poseta.getApoteka());
        rezervacija.setPacijent(poseta.getPacijent());
        rezervacija.setIzdato(true);
        posetaService.izvrsiPregled(pregledID, (String) podaci.get("zapisano"));
        rezervacija.setRezervisaniLijekovi( new ArrayList<>());
        List<Map<String, Object>> lijekovi = (List<Map<String, Object>>) podaci.get("lijekovi");

        for(Map<String, Object> lek: lijekovi){
            Long id = Long.parseLong(lek.get("id").toString());
            for(StanjeLijeka lekIzApoteke : poseta.getApoteka().getLijekovi()){
                if(lekIzApoteke.getId().equals(id)){
                    rezervacija.getRezervisaniLijekovi().add(lekIzApoteke);
                    lekIzApoteke.setKolicina(lekIzApoteke.getKolicina()- (Integer) lek.get("kolicina"));
                    break;
                }
            }
        }

        repository.save(rezervacija);
    }
}
