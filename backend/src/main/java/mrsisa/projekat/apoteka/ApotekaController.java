package mrsisa.projekat.apoteka;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import mrsisa.projekat.lijek.Lijek;
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
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Integer id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
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
                            false,
                            10.00,
                            LocalDateTime.parse("01-04-2021 10:10",format)
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
                            false,
                            11.00,
                            LocalDateTime.parse("01-04-2021 10:10",format)

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
                            true,
                            12.00,
                            LocalDateTime.parse("01-04-2021 10:10",format)
                    )
            );
    }

    @GetMapping(path="/dobaviApoteke")
    public List<Apoteka> dobaviApoteke(){
    	

    	return apotekaService.dobaviApoteke();
    }

    //ResponseEntity<Apoteka>
    @PostMapping(consumes = "application/json", path = "/sacuvajApoteku")
    public void sacuvajApoteku(@RequestBody ApotekaDTO dummy) {
        Apoteka a = new Apoteka(dummy);
        apotekaService.save(a);
    }
}
