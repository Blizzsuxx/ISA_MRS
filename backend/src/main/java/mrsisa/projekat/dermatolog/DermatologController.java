package mrsisa.projekat.dermatolog;

import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dermatolog")
public class DermatologController {
    private final DermatologService dermatologService;
    @Autowired
    public DermatologController(DermatologService dermatologService){
        this.dermatologService = dermatologService;
    }

    @GetMapping(path="/dobaviDermatologe")
    public List<Dermatolog> dobaviApoteke(){


        return dermatologService.dobaviDermatologe();
    }


    @PostMapping(consumes = "application/json", path = "/sacuvajDermatologa")
    public void sacuvajDermatologa(@RequestBody KorisnikDTO dummy) {
        Dermatolog d = new Dermatolog(dummy);
        this.dermatologService.save(d);
    }
}
