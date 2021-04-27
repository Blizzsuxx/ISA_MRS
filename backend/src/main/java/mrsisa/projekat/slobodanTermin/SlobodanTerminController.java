package mrsisa.projekat.slobodanTermin;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
