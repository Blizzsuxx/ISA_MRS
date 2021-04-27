package mrsisa.projekat.radnoVrijeme;


import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/radnoVrijeme")
public class RadnoVrijemeController {
    private final RadnoVrijemeService radnoVrijemeService;

    @Autowired
    public RadnoVrijemeController(RadnoVrijemeService radnoVrijemeService){
        this.radnoVrijemeService = radnoVrijemeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/{id}")
    public RadnoVrijemeDTO dobaviRadnoVrijeme(@PathVariable Integer id){

        return radnoVrijemeService.dobaviRadnoVrijeme(id);
    }
}
