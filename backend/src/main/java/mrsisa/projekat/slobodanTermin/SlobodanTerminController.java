package mrsisa.projekat.slobodanTermin;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/slobodanTermin")
public class SlobodanTerminController {
    final private SlobodanTerminService slobodanTerminService;


    public SlobodanTerminController(SlobodanTerminService slobodanTerminService){
        this.slobodanTerminService = slobodanTerminService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/dermatolog/{id}")
    public List<SlobodanTerminDTO> dobaviSlobodneTermineDermatologa(@PathVariable Integer id){
        return this.slobodanTerminService.dobaviSlobodneTermineDermatologa(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/apoteka/{id}")
    public List<SlobodanTerminDTO> dobaviSlobodneTermineApoteka(@PathVariable Long id){
        return this.slobodanTerminService.dobaviSlobodneTermineApoteka(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PostMapping("/")
    public void kreirajNoviTermin(@RequestBody SlobodanTerminDTO dto){
        this.slobodanTerminService.kreirajNoviTermin(dto);
    }
    @GetMapping("/slobodanTerminApoteke/{id}")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<SlobodanTerminDTO> slobodanTerminApoteke(@PathVariable String id){
        System.out.println(id);//May 11 2021 08 40 08 40 (Jun, Jul)
        System.out.println("mala lina");
        //ovo u sustini treba da vrati listu apoteka, ali cu vratiti odmah termine i odatle izvlaciti apoteke na frontu
        return this.slobodanTerminService.dobaviSlobodneTermine(id);
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @PutMapping("/zakaziFarmaceuta")
    public boolean zakazaiFarmaceuta(@RequestBody Object dto){
        this.slobodanTerminService.zakazi(dto);
        return true;
    }
}
