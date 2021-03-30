package mrsisa.projekat.poseta;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/apoteka")
public class PosetaController {
    private final PosetaService posetaService;
    @Autowired
    public PosetaController(PosetaService apotekaService){
        this.posetaService = apotekaService;
    }


    @GetMapping("/dobaviPosete/{id}")
    public List<Poseta> dobaviPosete(@PathVariable Long id){
        return posetaService.dobaviPosete(id);
    }

    @GetMapping(path="/dobaviPosete")
    public List<Poseta> dobaviApoteke(){
    	

    	return posetaService.dobaviPosete();
    }
    

}
