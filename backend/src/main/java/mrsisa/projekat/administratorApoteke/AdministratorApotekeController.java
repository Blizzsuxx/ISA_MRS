package mrsisa.projekat.administratorApoteke;

import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/administratorApoteke")
public class AdministratorApotekeController {
    private final AdministratorApotekeService administratorApotekeService;
    @Autowired
    public AdministratorApotekeController(AdministratorApotekeService administratorApotekeService) {
        this.administratorApotekeService = administratorApotekeService;
    }

    @PostMapping(consumes = "application/json", path = "/sacuvajAdministratoraApoteke")
    public void sacuvajAdministratoraApoteke(@RequestBody KorisnikDTO dummy) {
        AdministratorApoteke aa = new AdministratorApoteke(dummy);
        this.administratorApotekeService.save(aa);
    }

    @GetMapping(path = "/sviAdministratoriApoteke")
    public List<KorisnikDTO> vratiSveAdministratoreApoteka(){
        List<AdministratorApoteke> admini = this.administratorApotekeService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (AdministratorApoteke ap : admini)
            korisnici.add(new KorisnikDTO((Korisnik) ap));

        return korisnici;
    }
}
