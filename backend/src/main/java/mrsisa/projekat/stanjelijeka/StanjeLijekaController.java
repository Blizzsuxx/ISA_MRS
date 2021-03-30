package mrsisa.projekat.stanjelijeka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/stanjeLijeka")
public class StanjeLijekaController {
    private final StanjeLijekaService stanjeLijekaService;

    @Autowired
    public StanjeLijekaController(StanjeLijekaService stanjeLijekaService){
        this.stanjeLijekaService = stanjeLijekaService;
    }

    @PutMapping("/promjenaStatusaProdaje")
    public List<Long> promjenaStatusaProdaje(@RequestBody List<Long> identifikatori){
        return this.stanjeLijekaService.promjenaStatusaProdaje(identifikatori);
    }

    @PutMapping("/promjenaCijene")
    public void promjenaStatusaProdaje(@RequestParam Long id, @RequestParam double cijena,
                                             @RequestParam String datumIstekaCijene){
        System.out.println(id);
        System.out.println(cijena);
        System.out.println(datumIstekaCijene);
    }

}