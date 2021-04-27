package mrsisa.projekat.dermatolog;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.administratorSistema.AdministratorSistema;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/dobaviDermatologe")
    public List<DermatologDTO> dobaviDermatologe(){


        return dermatologService.dobaviDermatologe();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin")
    public List<DermatologDTO> dobaviDermatologeAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return dermatologService.dobaviDermatologeAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/otpustiDermatologa/{id}")
    public void otpustiDermatologa(@PathVariable Integer id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        dermatologService.otpustiDermatologa(id,adminApoteke.getApoteka().getId());
    }

    @PostMapping(consumes = "application/json", path = "/sacuvajDermatologa")
    public void sacuvajDermatologa(@RequestBody KorisnikDTO dummy) {
        Dermatolog d = new Dermatolog(dummy);
        this.dermatologService.save(d);
    }

    @GetMapping(path = "/sviDermatolozi")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveDermatologe(){
        List<Dermatolog> dermatolozi = this.dermatologService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (Dermatolog d : dermatolozi)
            korisnici.add(new KorisnikDTO((Korisnik) d));

        return korisnici;
    }
}
