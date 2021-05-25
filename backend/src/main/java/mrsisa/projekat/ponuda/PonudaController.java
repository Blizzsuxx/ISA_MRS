package mrsisa.projekat.ponuda;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.narudzbenica.NarudzbenicaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/ponuda")
public class PonudaController {
    private final PonudaService ponudaService;

    @Autowired
    public PonudaController(PonudaService ponudaService){
        this.ponudaService = ponudaService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/narudzbenica/{id}/admin")
    public List<PonudaDTO> dobaviSvePonudeNarudzbeniceAdmin(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return this.ponudaService.dobaviSvePonudeNarudzbeniceAdmin(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="narudzbenica/{n_id}/prihvati/{id}")
    public void prihvatiPonudu(@PathVariable Long n_id,@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        this.ponudaService.prihvatiPonudu(id,n_id,adminApoteke);
    }


    @PreAuthorize("hasRole('ROLE_DOBAVLJAC')")
    @GetMapping(path="/dobaviPonudeDobavljaca")
    public List<PonudaDTO> dobaviPonudeDobavljaca(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Dobavljac d = (Dobavljac) auth.getPrincipal();
        return this.ponudaService.dobaviSvePonudeDostavljaca(d);
    }
}
