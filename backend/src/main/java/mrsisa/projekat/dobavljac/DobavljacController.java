package mrsisa.projekat.dobavljac;

import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dobavljac")
public class DobavljacController {
    private final DobavljacService dobavljacService;

    @Autowired
    public DobavljacController(DobavljacService dobavljacService){
        this.dobavljacService = dobavljacService;
    }

    @PostMapping(consumes = "application/json", path = "/sacuvajDobavljaca")
    public void sacuvajDobavljaca(@RequestBody KorisnikDTO dummy) {
        Dobavljac d = new Dobavljac(dummy);
        this.dobavljacService.save(d);
    }
}
