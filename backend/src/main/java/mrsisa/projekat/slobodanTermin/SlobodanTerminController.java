package mrsisa.projekat.slobodanTermin;


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

    @GetMapping("/dermatolog/{id}")
    public List<SlobodanTerminDTO> dobaviSlobodneTermineDermatologa(@PathVariable Integer id){
        return this.slobodanTerminService.dobaviSlobodneTermineDermatologa(id);
    }

    @GetMapping("/apoteka/{id}")
    public List<SlobodanTerminDTO> dobaviSlobodneTermineApoteka(@PathVariable Long id){
        return this.slobodanTerminService.dobaviSlobodneTermineApoteka(id);
    }

    @PostMapping("/")
    public void kreirajNoviTermin(@RequestBody SlobodanTerminDTO dto){
        this.slobodanTerminService.kreirajNoviTermin(dto);
    }
}
