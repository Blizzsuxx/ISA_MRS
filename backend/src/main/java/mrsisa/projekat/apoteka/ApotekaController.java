package mrsisa.projekat.apoteka;


import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/apoteka")
public class ApotekaController {
    private final ApotekaService apotekaService;
    @Autowired
    public ApotekaController(ApotekaService apotekaService){
        this.apotekaService = apotekaService;
    }


    @GetMapping()
    public List<StanjeLijeka> dobaviLijekove(){
        return apotekaService.dobaviStanjaLijekova();
    }
}
