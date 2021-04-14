package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/DTOlijekovi")
    public List<LijekDTO> dobaviSveDTOLijekove() { return lijekService.dobaviSveDTOLijekove(); }

    @PostMapping(consumes = "application/json", path = "/sacuvajLijek")
    public void sacuvajLijek(@RequestBody LijekDTO dummy) {
        Lijek l = new Lijek(dummy);
        lijekService.save(l);
    }
}
