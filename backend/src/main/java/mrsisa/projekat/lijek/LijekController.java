package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.dobavljac.Dobavljac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/lijekovi")
public class LijekController {

    private final LijekService lijekService;
    @Autowired
    public LijekController(LijekService lijekService){
        this.lijekService = lijekService;
    }
    @GetMapping()
    public List<Lijek> dobaviSveLijekove(){
        return lijekService.dobaviSveLijekove();
    }

    @PreAuthorize("hasAnyRole('ADMIN_APOTEKA','ADMIN_SISTEMA')")
    @GetMapping("/DTOlijekovi")
    public List<LijekDTO> dobaviSveDTOLijekove() {
        return this.lijekService.dobaviSveDTOLijekove();
    }

    @GetMapping("/dobaviBasSveLijekove")
    public List<LijekDTO> dobaviBasSveLijekove() {
        return this.lijekService.dobaviSveDTOLijekove();
    }

    @GetMapping("/dobaviSveLijekoveApotekeDTO/{id}")
    public List<LijekApotekaDTO> dobaviSveLijekoveApotekeDTO(@PathVariable long id){
        return this.lijekService.dobaviSveApotekaLijekDTO(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','DOBAVLJAC')")
    @GetMapping(produces = "application/json", value = "/dobaviDTOLijek/{naziv}")
    public LijekDTO getLijekDTO(@PathVariable String naziv){
        Lijek l = this.lijekService.findByNaziv(naziv);
        if (l == null){
            return null;
        }
        return new LijekDTO(l);
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PutMapping(consumes = "application/json", value = "/azurirajDTOLijek")
    public boolean azurirajLijek(@RequestBody LijekDTO dummy){
        Lijek l = this.lijekService.findByNaziv(dummy.getNaziv());

        if (l == null)
            return false;

        l.setNapomena(dummy.getNapomena());
        l.setOblikLijeka(dummy.getOblikLijeka());
       // l.setOcijena(dummy.getOcijena()); TODO ocene su liste
        l.setProizvodjac(dummy.getProizvodjac());
        l.setSastav(dummy.getSastav());
        l.setVrstaLijeka(dummy.getVrstaLijeka());
        l.setPoeni(dummy.getPoeni());

        this.lijekService.save(l);
        return true;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajLijek")
    public void sacuvajLijek(@RequestBody LijekDTO dummy) {
        this.lijekService.sacuvajLijek(dummy);
    }

    @Transactional
    @DeleteMapping(value="/obrisiDTOLijek/{naziv}")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public boolean obrisiDTOLijek(@PathVariable String naziv){
        Lijek l = this.lijekService.findByNaziv(naziv);
        if (l != null){
            this.lijekService.remove(naziv);
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_DOBAVLJAC')")
    @GetMapping(value="/dobaviStanjeLijekovaDobavljaca")
    public List<LijekDTO> dobaviStanjeLijekovaDobavljaca(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Dobavljac d = (Dobavljac) auth.getPrincipal();
        return this.lijekService.dobaviLijekoveDobavljaca(d);
    }
}
