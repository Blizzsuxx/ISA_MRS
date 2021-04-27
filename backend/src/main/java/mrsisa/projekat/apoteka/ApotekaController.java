package mrsisa.projekat.apoteka;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.lijek.Lijek;
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
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Long id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return apotekaService.dobaviStanjaLijekova(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PostMapping(value="/dobaviLijekoveApoteke")
    public List<StanjeLijeka> dobaviLijekoveApoteke(@RequestBody Map<String, Object> params){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return apotekaService.dobaviStanjaLijekova(Long.valueOf(1));
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
    public List<Apoteka> dobaviApoteke(){
    	

    	return apotekaService.dobaviApoteke();
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


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/{id}")
    public ApotekaDTO dobaviApoteku(@PathVariable Long id){


        return apotekaService.dobaviApoteku(id);
    }




    @PostMapping(consumes = "application/json", path = "/sacuvajApoteku")
    public void sacuvajApoteku(@RequestBody ApotekaDTO dummy) {
        Apoteka a = new Apoteka(dummy);
        apotekaService.save(a);
    }
}
