package mrsisa.projekat.zalba;

import mrsisa.projekat.korisnik.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/odgovori")
public class OdgovorController {

    private final OdgovorService odgovorService;

    @Autowired
    public OdgovorController(OdgovorService odgovorService){
        this.odgovorService = odgovorService;
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @PostMapping(consumes = "application/json", path = "/sacuvajOdgovor")
    public void sacuvajOdgovor(@RequestBody Odgovor odgovor){
        this.odgovorService.save(odgovor);
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @GetMapping(value="/dobaviOdgovoreZalbe/{id}")
    public List<Odgovor> dobaviOdgovoreZalbe(@PathVariable int id){
        return this.odgovorService.findAllByZalba(id);
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(value="/kreirajOdgovor")
    public void kreirajOdgovor(@RequestBody OdgovorDTO odgovor){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        odgovor.setKorisnickoIme(k.getUsername());
        this.odgovorService.sacuvaj(odgovor);
    }
}
