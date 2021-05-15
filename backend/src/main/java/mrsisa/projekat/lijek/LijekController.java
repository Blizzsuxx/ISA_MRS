package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        List<Lijek> lijekovi = this.lijekService.findAll();
        List<LijekDTO> lijekoviDTO = new ArrayList<>();

        for (Lijek l : lijekovi)
            lijekoviDTO.add(new LijekDTO(l));

        return lijekoviDTO;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
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
        l.setOcijena(dummy.getOcijena());
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
        Lijek l = new Lijek(dummy);
        lijekService.save(l);
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

}
