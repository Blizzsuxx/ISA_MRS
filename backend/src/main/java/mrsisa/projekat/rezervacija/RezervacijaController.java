package mrsisa.projekat.rezervacija;

import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.poseta.PosetaService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(path="api/v1/rezervacije")
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;
    private final PosetaService posetaService;

    @Autowired
    public RezervacijaController(RezervacijaService rezervacijaService, PosetaService posetaService) {
        this.rezervacijaService = rezervacijaService;
        this.posetaService = posetaService;
    }

    @PostMapping(value="/postaviRezervacije")
    public void postaviRezervacije( @RequestBody Map<String, Object> podaci){


        return;
    }
    @PostMapping(value = "/postaviRezervaciju")
    public void postaviRezervaciju(@RequestBody Map<String, Object> podaci){
        this.rezervacijaService.postaviRezervaciju(podaci, posetaService);
    }

    @PostMapping(value="/dobaviRezervacijeId")
    public List<RezervacijaDTO> dobaviRezervacijeId( @RequestBody Map<String, Object> podaci){
        return this.rezervacijaService.dobaviRezervacijeId(podaci);

    }

    @PostMapping(value = "/IzdajLek")
    public void IzdajLek(@RequestBody Map<String, Object> podaci){
        this.rezervacijaService.IzdajLek(podaci);
    }


}
