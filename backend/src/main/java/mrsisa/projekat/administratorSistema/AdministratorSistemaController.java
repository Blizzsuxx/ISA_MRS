package mrsisa.projekat.administratorSistema;

import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/administratorSistema")
public class AdministratorSistemaController {
    private final AdministratorSistemaService administratorSistemaService;

    @Autowired
    public AdministratorSistemaController(AdministratorSistemaService administratorSistemaService) {
        this.administratorSistemaService = administratorSistemaService;
    }

    @PostMapping(consumes = "application/json", path = "/sacuvajAdministratoraSistema")
    public void sacuvajAdministratoraSistema(@RequestBody KorisnikDTO dummy) {
        AdministratorSistema as = new AdministratorSistema(dummy);
        this.administratorSistemaService.save(as);
    }

}
