package mrsisa.projekat.lijek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
