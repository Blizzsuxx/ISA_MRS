package mrsisa.projekat.apoteka;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/apoteka")
public class ApotekaController {
    private final ApotekaService apotekaService;
    @Autowired
    public ApotekaController(ApotekaService apotekaService){
        this.apotekaService = apotekaService;
    }


    @GetMapping("/dobaviLijekove/{id}")
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Integer id){
        return apotekaService.dobaviStanjaLijekova();
    }
    @GetMapping(path="/dobaviApoteke")
    public List<Apoteka> dobaviApoteke(){
    	

    	return apotekaService.dobaviApoteke();
    }
    

}
