package mrsisa.projekat.rezervacija;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RezervacijaService {
    private final RezervacijaRepository repository;
    private final ApotekaRepository apotekaRepository;

    @Autowired
    public RezervacijaService(RezervacijaRepository repository, ApotekaRepository apotekaRepository) {
        this.repository = repository;
        this.apotekaRepository = apotekaRepository;
    }

    @Transactional
    public void postaviRezervaciju(Map<String, Object> podaci, PosetaService posetaService) {
        Long pregledID = Long.parseLong(podaci.get("pregledID").toString());
        Poseta poseta = posetaService.findId(pregledID);
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatumRezervacije(LocalDateTime.now());
        rezervacija.setApoteka(poseta.getApoteka());
        rezervacija.setPacijent(poseta.getPacijent());
        rezervacija.setIzdato(false);
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

    @Transactional
    public List<RezervacijaDTO> dobaviRezervacijeId(Map<String, Object> podaci) {


        Long id = Long.parseLong(podaci.get("id").toString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Farmaceut k = (Farmaceut)auth.getPrincipal();
        Apoteka apoteka = this.apotekaRepository.findOneById(k.getApoteka().getId());
        for(Rezervacija r : apoteka.getRezervacije()){
            if(r.getId() == id){
                ArrayList<RezervacijaDTO> dto = new ArrayList<>();

                for(StanjeLijeka token : r.getRezervisaniLijekovi()){
                    RezervacijaDTO rez = new RezervacijaDTO(r, token);
                    dto.add(rez);

                }
                return dto;
            }
        }
        return null;


    }
}
