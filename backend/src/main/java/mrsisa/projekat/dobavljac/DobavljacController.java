package mrsisa.projekat.dobavljac;

import mrsisa.projekat.dermatolog.DermatologService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dobavljac")
public class DobavljacController {
    private final DobavljacService dobavljacService;

    @Autowired
    public DobavljacController(DobavljacService dobavljacService){
        this.dobavljacService = dobavljacService;
    }

}
