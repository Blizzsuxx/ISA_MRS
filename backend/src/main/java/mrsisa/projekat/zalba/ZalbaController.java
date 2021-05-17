package mrsisa.projekat.zalba;

import mrsisa.projekat.korisnik.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/zalbe")
public class ZalbaController {
    private final ZalbaService zalbaService;

    @Autowired
    public ZalbaController(ZalbaService zalbaService){
        this.zalbaService = zalbaService;
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @PostMapping(consumes = "application/json", path = "/sacuvajZalbu")
    public void sacuvajZalbu(@RequestBody Zalba z){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        z.setPacijent(k.getUsername());
        this.zalbaService.save(z);
    }
}
