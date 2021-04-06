package mrsisa.projekat.administratorApoteke;

import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
}
