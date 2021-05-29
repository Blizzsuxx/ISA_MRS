package mrsisa.projekat.dermatolog;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.administratorSistema.AdministratorSistema;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.radnoVrijeme.RadnoVrijemeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dermatolog")
public class DermatologController {
    private final DermatologService dermatologService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DermatologController(DermatologService dermatologService){
        this.dermatologService = dermatologService;
    }


    @GetMapping(path="/dobaviDermatologe")
    public List<DermatologDTO> dobaviDermatologe(){


        return dermatologService.dobaviDermatologe();
    }


    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin")
    public List<DermatologDTO> dobaviDermatologeAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return dermatologService.dobaviDermatologeAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(path="/korisnik/{id}")
    public List<DermatologDTO> dobaviDermatologeKorisnik(@PathVariable Long id){

        return dermatologService.dobaviDermatologeAdmin(id);
    }



    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin/nezaposleni")
    public List<DermatologDTO> dobaviNezaposleneDermatologeAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return dermatologService.dobaviNezaposleneDermatologeAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/otpustiDermatologa/{id}")
    public void otpustiDermatologa(@PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        dermatologService.otpustiDermatologa(id,adminApoteke.getApoteka().getId());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PostMapping(path="/zaposliDermatologa")
    public void zaposliDermatologa(@RequestBody RadnoVrijemeDTO radnoVrijemeDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        dermatologService.zaposliDermatologa(radnoVrijemeDTO,adminApoteke.getApoteka().getId());
    }

    @PostMapping(consumes = "application/json", path = "/sacuvajDermatologa")
    public void sacuvajDermatologa(@RequestBody KorisnikDTO dummy) {
        dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
        Dermatolog d = new Dermatolog(dummy);
        this.dermatologService.save(d);
    }

    @GetMapping(path = "/sviDermatolozi")
    @PreAuthorize("hasRole('ADMIN_SISTEMA') or hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
    public List<KorisnikDTO> vratiSveDermatologe(){
        List<Dermatolog> dermatolozi = this.dermatologService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (Dermatolog d : dermatolozi)
            korisnici.add(new KorisnikDTO((Korisnik) d));

        return korisnici;
    }
}
