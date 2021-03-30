package mrsisa.projekat.Dermatolog;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaService;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dermatolog")
public class DermatologControler {


    private final DermatologServis dermatologService;
    @Autowired
    public DermatologControler(DermatologServis apotekaService){
        this.dermatologService = apotekaService;
    }

    @GetMapping(path="/dobaviDermatologe")
    public List<Dermatolog> dobaviApoteke(){


        return dermatologService.dobaviDermatologe();
    }



}
