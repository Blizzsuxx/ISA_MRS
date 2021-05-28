package mrsisa.projekat.erecept;

import mrsisa.projekat.poseta.PosetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/erecepti")
public class EreceptController {

    private final EreceptService ereceptService;
    private final PosetaService posetaService;

    @Autowired
    public EreceptController(EreceptService ereceptService, PosetaService posetaService) {
        this.ereceptService = ereceptService;
        this.posetaService = posetaService;
    }

    @PostMapping("/postaviErecept")
    public void postaviErecept(@RequestBody Map<String, Object> podaci){
        this.ereceptService.postaviErecept(podaci, posetaService);
    }

}
