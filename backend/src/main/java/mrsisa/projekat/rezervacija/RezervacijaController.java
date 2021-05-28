package mrsisa.projekat.rezervacija;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(path="api/v1/rezervacije")
public class RezervacijaController {

    @PostMapping(value="/postaviRezervacije")
    public void postaviRezervacije( @RequestBody Map<String, Object> podaci){


        return;
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
