package mrsisa.projekat.dobavljac;

import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dobavljac")
public class DobavljacController {
    private final DobavljacService dobavljacService;

    @Autowired
    public DobavljacController(DobavljacService dobavljacService){
        this.dobavljacService = dobavljacService;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajDobavljaca")
    public void sacuvajDobavljaca(@RequestBody KorisnikDTO dummy) {
        Dobavljac d = new Dobavljac(dummy);
        this.dobavljacService.save(d);
    }

    @GetMapping(path = "/sviDobavljaci")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveDobavljace(){
        List<Dobavljac> dobavljaci = this.dobavljacService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (Dobavljac d : dobavljaci)
            korisnici.add(new KorisnikDTO((Korisnik) d));

        return korisnici;
    }
}
