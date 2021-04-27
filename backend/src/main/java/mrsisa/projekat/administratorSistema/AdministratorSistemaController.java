package mrsisa.projekat.administratorSistema;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/administratorSistema")
public class AdministratorSistemaController {
    private final AdministratorSistemaService administratorSistemaService;

    @Autowired
    public AdministratorSistemaController(AdministratorSistemaService administratorSistemaService) {
        this.administratorSistemaService = administratorSistemaService;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajAdministratoraSistema")
    public void sacuvajAdministratoraSistema(@RequestBody KorisnikDTO dummy) {
        AdministratorSistema as = new AdministratorSistema(dummy);
        this.administratorSistemaService.save(as);
    }

    @GetMapping(path = "/sviAdministratoriSistema")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveAdministratoreSistema(){
        List<AdministratorSistema> admini = this.administratorSistemaService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (AdministratorSistema as : admini)
            korisnici.add(new KorisnikDTO((Korisnik) as));

        return korisnici;
    }
}
