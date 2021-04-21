package mrsisa.projekat.rezervacija;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(path="api/v1/rezervacije")
public class RezervacijaController {

    @PostMapping(value="/postaviRezervacije")
    public void postaviRezervacije( @RequestBody Map<String, Object> podaci){
        return;
    }
}
