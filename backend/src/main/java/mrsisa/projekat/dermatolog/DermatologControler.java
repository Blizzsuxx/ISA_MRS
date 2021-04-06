package mrsisa.projekat.dermatolog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/dermatolog")
public class DermatologControler {


    private final DermatologService dermatologService;
    @Autowired
    public DermatologControler(DermatologService apotekaService){
        this.dermatologService = apotekaService;
    }

    @GetMapping(path="/dobaviDermatologe")
    public List<Dermatolog> dobaviApoteke(){


        return dermatologService.dobaviDermatologe();
    }



}
