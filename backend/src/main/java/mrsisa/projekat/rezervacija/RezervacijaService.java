package mrsisa.projekat.rezervacija;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.popust.Popust;
import mrsisa.projekat.popust.PopustService;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RezervacijaService {
    private final RezervacijaRepository repository;
    private final ApotekaRepository apotekaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final PopustService popustService;


    @Autowired
    public RezervacijaService(RezervacijaRepository repository, ApotekaRepository apotekaRepository, StanjeLijekaRepository stanjeLijekaRepository, PopustService popustService) {
        this.repository = repository;
        this.apotekaRepository = apotekaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.popustService = popustService;
    }

    @Transactional
    public Boolean postaviRezervaciju(Map<String, Object> podaci, PosetaService posetaService) {

        Long pregledID = Long.parseLong(podaci.get("pregledID").toString());

        Poseta poseta = posetaService.findId(pregledID);

        Boolean greska = posetaService.proveriDostupnost(podaci, stanjeLijekaRepository);

        if(greska){
            return true;
        }

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatumRezervacije(LocalDateTime.now().plusDays(2));
        rezervacija.setApoteka(poseta.getApoteka());

        rezervacija.setPacijent(poseta.getPacijent());
        rezervacija.setIzdato(false);
        posetaService.izvrsiPregled(pregledID, (String) podaci.get("zapisano"));
        rezervacija.setRezervisaniLijekovi( new ArrayList<>());
        List<Map<String, Object>> lijekovi = (List<Map<String, Object>>) podaci.get("lijekovi");
        poseta.getApoteka().getRezervacije().add(rezervacija);
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
        return false;
    }

    @Transactional
    public List<RezervacijaDTO> dobaviRezervacijeId(Map<String, Object> podaci) {


        Long id = Long.parseLong(podaci.get("id").toString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Farmaceut k = (Farmaceut)auth.getPrincipal();
        Apoteka apoteka = this.apotekaRepository.findOneById(k.getApoteka().getId());
        for(Rezervacija r : apoteka.getRezervacije()){
            if(r.getId().equals(id) && ChronoUnit.HOURS.between(LocalDateTime.now(), r.getDatumRezervacije()) >= 24 && !r.isIzdato()){
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


    @Transactional
    public void IzdajLek(Map<String, Object> podaci) {
        Long id = Long.parseLong(podaci.get("id").toString());
        Rezervacija r = this.repository.findById1(id);
        r.setIzdato(true);
        String kategorija = r.getPacijent().getKategorija();
        Popust p = this.popustService.findById(1);
        // TODO BODOVI
    }
}
