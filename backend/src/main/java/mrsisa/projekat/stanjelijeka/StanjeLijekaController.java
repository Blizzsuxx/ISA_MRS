package mrsisa.projekat.stanjelijeka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
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
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/izbrisiLijekove")
    public void izbrisiLijekove(@RequestBody List<Long> identifikatori){
        this.stanjeLijekaService.izbrisiLijekove(identifikatori);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/promjeniCijenu")
    public void promjeniCijenu(@RequestParam Long id, @RequestParam double cijena,
                                             @RequestParam String datumIstekaCijene){
        this.stanjeLijekaService.promjeniCijenu(id,cijena,datumIstekaCijene);
    }

    @GetMapping("/dobaviLijekove1/{id}")
    public List<StanjeLijeka> dobaviLijekove(@PathVariable Long id){

        return stanjeLijekaService.dobaviStanjaLijekova(id);
    }



}
