package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/odgovori")
public class OdgovorController {

    private final OdgovorService odgovorService;

    @Autowired
    public OdgovorController(OdgovorService odgovorService){
        this.odgovorService = odgovorService;
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @PostMapping(consumes = "application/json", path = "/sacuvajOdgovor")
    public void sacuvajOdgovor(@RequestBody Odgovor odgovor){
        this.odgovorService.save(odgovor);
    }

    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'PACIJENT')")
    @GetMapping(value="/dobaviOdgovoreZalbe/{id}")
    public List<Odgovor> dobaviOdgovoreZalbe(@PathVariable int id){
        return this.odgovorService.findAllByZalba(id);
    }
}
