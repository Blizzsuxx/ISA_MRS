package mrsisa.projekat.apoteka;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/apoteka")
public class ApotekaController {
    private final ApotekaService apotekaService;
    @Autowired
    public ApotekaController(ApotekaService apotekaService){
        this.apotekaService = apotekaService;
    }


    @GetMapping("/dobaviLijekove/{id}")
    public List<StanjeLijekaDTO> dobaviLijekove(@PathVariable Long id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return apotekaService.dobaviStanjaLijekova(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/lijekovi/admin")
    public List<StanjeLijekaDTO> dobaviLijekoveAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return apotekaService.dobaviStanjaLijekovaAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping("{id}/lijekovi/profil")
    public List<StanjeLijekaDTO> dobaviLijekoveProfil(@PathVariable Long id){

        return apotekaService.dobaviStanjaLijekova(id);
    }

    @GetMapping("/dobaviLijekoveN")
    public List<StanjeLijekaDTO> dobaviLijekoveNeautentifikovan(){

        return apotekaService.dobaviSveDostupneLijekove();
    }

    @GetMapping("/dobaviLijekoveA/{id}")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<LijekDTO> dobaviLijekoveA(@PathVariable Long id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return apotekaService.dobaviLijekoveAlergija(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PostMapping(value="/dobaviLijekoveApoteke")
    public List<StanjeLijekaDTO> dobaviLijekoveApoteke(@RequestBody Map<String, Object> params){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return apotekaService.dobaviStanjaLijekova(Long.valueOf(1));
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value="/dobaviSveDostupneLijekove")
    public List<StanjeLijekaDTO> dobaviDostupneLijekove(){
        //TODO: cisto da ne zaboravim, ovo treba da vrati listu svih stnja lekova u svim apotekama, dozvoljeno je da se i lekovi i apoteke ponavljaju
        //bitno je da ima ime apoteke, kolicinu koja je dostupna, cenu....
        return apotekaService.dobaviSveDostupneLijekove();
    }
    @PreAuthorize("hasRole('PACIJENT')")
    @PostMapping(value="/rezervisiLek")
    public boolean rezervisiLek(@RequestBody String lek){
        //TODO: rezervisanje
        //return apotekaService.dobaviSveDostupneLijekove();
        System.out.println(lek);

        return  apotekaService.rezervisiLek(lek);
    }

    @PostMapping(value="/proveriAlergije")
    public Boolean proveriAlergije(@RequestBody Map<String, Object> params){

        return false;
    }

    @PostMapping(value="/proveriDostupnost")
    public Boolean proveriDostupnost(@RequestBody Map<String, Object> params){
        return false;
    }

    @GetMapping(path="/dobaviApoteke")
    public List<ApotekaDTO> dobaviApoteke(){
    	return apotekaService.dobaviApoteke();
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value="/dobaviApotekePacijenta")
    public List<ApotekaDTO> dobaviApotekePacijenta(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pacijent pacijent = (Pacijent)auth.getPrincipal();
        return apotekaService.dobaviApotekePacijenta(pacijent);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin")
    public ApotekaDTO dobaviApotekuAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return apotekaService.dobaviApotekuAdmin(adminApoteke);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/admin")
    public ResponseEntity<?> azurirajApotekuAdmin(@RequestBody ApotekaDTO apoteka){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        if( adminApoteke.getApoteka().getId().equals(apoteka.getId())==false){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        apotekaService.azurirajApotekuAdmin(adminApoteke,apoteka);
        return new ResponseEntity<String>( HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('PACIJENT','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/{id}")
    public ApotekaDTO dobaviApoteku(@PathVariable Long id){


        return apotekaService.dobaviApoteku(id);
    }



    //ResponseEntity<Apoteka>
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajApoteku")
    public void sacuvajApoteku(@RequestBody ApotekaDTO dummy) {
        //Apoteka a = new Apoteka(dummy);
        apotekaService.sacuvajApoteku(dummy);

    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/pretplataApoteke/{idApoteke}")
    public boolean pretplatiSeNaAApoteku(@PathVariable long idApoteke){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pacijent p = (Pacijent)auth.getPrincipal();
        return apotekaService.pretplatiSeNaAApoteku(p, idApoteke);
    }


}
