package mrsisa.projekat.akcija;


import mrsisa.projekat.lijek.LijekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/akcija")
public class AkcijaController {

    private final AkcijaService akcijaService;
    @Autowired
    public AkcijaController(AkcijaService aService){
        this.akcijaService = aService;
    }

    //@PreAuthorize("hasRole('PACIJENT')")
    @PutMapping( value = "/dobaviAkcije/{id}")
    public List<AkcijaDTO> getAkcijaDTO(@PathVariable String id){
        return this.akcijaService.vratiAkcije(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PostMapping(value = "/")
    public void kreirajAkciju( @RequestBody Map<String, Object> podaci ){
        this.akcijaService.kreirajAkciju(podaci);
    }


}
