package mrsisa.projekat.radnoVrijeme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/radnoVrijeme")
public class RadnoVrijemeController {
    private final RadnoVrijemeService radnoVrijemeService;

    @Autowired
    public RadnoVrijemeController(RadnoVrijemeService radnoVrijemeService){
        this.radnoVrijemeService = radnoVrijemeService;
    }
}
