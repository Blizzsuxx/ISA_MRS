package mrsisa.projekat.apoteka;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.servlet.http.HttpServletRequest;


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
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Long id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return apotekaService.dobaviStanjaLijekova(id);
    }

    @GetMapping(path="/dobaviApoteke")
    public List<Apoteka> dobaviApoteke(){
    	

    	return apotekaService.dobaviApoteke();
    }

    @GetMapping(path="/{id}")
    public ApotekaDTO dobaviApoteku(@PathVariable Long id){


        return apotekaService.dobaviApoteku(id);
    }



    //ResponseEntity<Apoteka>
    @PostMapping(consumes = "application/json", path = "/sacuvajApoteku")
    public void sacuvajApoteku(@RequestBody ApotekaDTO dummy) {
        Apoteka a = new Apoteka(dummy);
        apotekaService.save(a);
    }
}
