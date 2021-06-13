package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/stanjeLijeka")
public class StanjeLijekaController {
    private final StanjeLijekaService stanjeLijekaService;

    @Autowired
    public StanjeLijekaController(StanjeLijekaService stanjeLijekaService){
        this.stanjeLijekaService = stanjeLijekaService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @DeleteMapping("/izbrisiLijekove/{id}")
    public int izbrisiLijekove(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return this.stanjeLijekaService.izbrisiLijekove(id,adminApoteke.getApoteka().getId());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/promjeniCijenu")
    public void promjeniCijenu(@RequestParam Long id, @RequestParam double cijena,
                                             @RequestParam String datumIstekaCijene){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        boolean greska =  this.stanjeLijekaService.promjeniCijenu(id,cijena,datumIstekaCijene,adminApoteke.getApoteka().getId());
        if (greska){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/dobaviZatrazene")
    public List<StanjeLijekaDTO> dobaviZatrazene(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return this.stanjeLijekaService.dobaviZatrazene(adminApoteke);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/ocistiZatrazeni/{id}")
    public List<StanjeLijekaDTO> ocistiZatrazeni(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        this.stanjeLijekaService.ocistiZatrazeni(id);
        return this.stanjeLijekaService.dobaviZatrazene(adminApoteke);
    }


    @GetMapping("/dobaviLijekove1/{id}")
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Long id){

        return stanjeLijekaService.dobaviStanjaLijekova(id);
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @PutMapping("/setujPotraznju/{id}")
    public void setujPotraznju(@PathVariable Long id){

      this.stanjeLijekaService.dodajPotraznju(id);
    }



}
