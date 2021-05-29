package mrsisa.projekat.farmaceut;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.dermatolog.DermatologDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/farmaceut")
public class  FarmaceutController {
    private final FarmaceutService farmaceutService;
    public FarmaceutController(FarmaceutService farmaceutService){
        this.farmaceutService = farmaceutService;
    }


    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin")
    public List<DermatologDTO> dobaviFarmaceuteAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return farmaceutService.dobaviFarmaceuteAdmin(adminApoteke.getApoteka().getId());
    }


    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin/nezaposleni")
    public List<DermatologDTO> dobaviNezaposleneFarmaceuteAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return farmaceutService.dobaviNezaposleneFarmaceuteAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/otpustiFarmaceuta/{id}")
    public void otpustiFarmaceuta(@PathVariable Integer id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        farmaceutService.otpustiDermatologa(id,adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/zaposliFarmaceuta/{id}")
    public void zaposliFarmaceuta(@PathVariable Integer id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        farmaceutService.zaposliFarmaceuta(id,adminApoteke.getApoteka().getId());
    }

}
