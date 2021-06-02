package mrsisa.projekat.rezervacija;

import mrsisa.projekat.poseta.PosetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        RezervacijaDTO a = new RezervacijaDTO("lek", "dobar", 11, 12.0, "poteka", "12-12-2022");

        if(Integer.valueOf(podaci.get("id").toString()) == 2){
            return null;
        }
      return List.of(a);
    }

}
