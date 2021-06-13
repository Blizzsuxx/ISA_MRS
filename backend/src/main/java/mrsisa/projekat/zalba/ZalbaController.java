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
@RequestMapping(path="api/v1/zalbe")
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

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @GetMapping(path = "/dobaviZalbe")
    public List<Zalba> dobaviZalbe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return this.zalbaService.findAllByPacijent(k.getUsername());
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @GetMapping(path = "/dobaviSveZalbe")
    public List<Zalba> dobaviSveZalbe(){
        return this.zalbaService.findAll();
    }


}
