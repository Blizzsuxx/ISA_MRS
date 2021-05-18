package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/odgovori")
public class OdgovorController {

    private final OdgovorService odgovorService;

    @Autowired
    public OdgovorController(OdgovorService odgovorService){
        this.odgovorService = odgovorService;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajOdgovor")
    public void sacuvajOdgovor(@RequestBody Odgovor odgovor){
        this.odgovorService.save(odgovor);
    }
}
