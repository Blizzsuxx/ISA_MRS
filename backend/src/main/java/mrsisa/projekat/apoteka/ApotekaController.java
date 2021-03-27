package mrsisa.projekat.apoteka;


import java.util.List;

import mrsisa.projekat.lijek.Lijek;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Integer id){
        return List.of(
                    new StanjeLijeka(
                            1L,
                            new Lijek(
                                    1L,
                                    "Paracetamol",
                                    "Protiv boli",
                                    "tableta",
                                    "ljiek",
                                    "Biofarm",
                                    "Lijek"
                            ),
                            10,
                            false
                    ),
                    new StanjeLijeka(
                            2L,
                            new Lijek(
                                    2L,
                                    "Brufen",
                                    "Protiv boli",
                                    "tableta",
                                    "ljiek",
                                    "Biofarm",
                                    "Lijek"
                            ),
                            20,
                            false

                    ),
                    new StanjeLijeka(

                            3L,
                            new Lijek(
                                    3L,
                                    "Lekadol",
                                    "Protiv boli",
                                    "tableta",
                                    "ljiek",
                                    "Biofarm",
                                    "Lijek"
                            ),
                            15,
                            true
                    )
            );
    }

    @GetMapping(path="/dobaviApoteke")
    public List<Apoteka> dobaviApoteke(){
    	

    	return apotekaService.dobaviApoteke();
    }
    

}
