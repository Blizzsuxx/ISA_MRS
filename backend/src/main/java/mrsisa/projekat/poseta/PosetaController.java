package mrsisa.projekat.poseta;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.slobodanTermin.SlobodanTerminDTO;
import mrsisa.projekat.slobodanTermin.SlobodanTerminService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/posete")
public class PosetaController {
    private final PosetaService posetaService;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final ApotekaRepository apotekaRepository;
    private final LijekRepository lijekRepository;

    @Autowired
    public PosetaController(PosetaService apotekaService, StanjeLijekaRepository stanjeLijekaRepository, ApotekaRepository apotekaRepository, LijekRepository lijekRepository){
        this.posetaService = apotekaService;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.apotekaRepository = apotekaRepository;
        this.lijekRepository = lijekRepository;
    }

    @PostMapping("/zabeleziOdsustvo")
    @PreAuthorize("hasAnyRole('DERMATOLOG', 'FARMACEUT')")
    public void zabeleziOdsustvo(@RequestBody Map<String, Object> data){

        posetaService.zabeleziOdsustvo(Long.parseLong(data.get("id").toString()));
    }

    @GetMapping("/dobaviPosete/{id}")
    public List<PosetaDTO> dobaviPosete(@PathVariable Long id){
        return posetaService.dobaviPosete(id);
    }


    @GetMapping("/dobaviPosetePacijenta")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<PosetaDTO> dobaviPosetePacijenta(){
        return posetaService.dobaviPosetePacijenta("9");
    }
    @GetMapping("/dobaviPosetePacijentaF")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<PosetaDTO> dobaviPosetePacijentaF(){
        return posetaService.dobaviPosetePacijentaF("9");
    }

    @GetMapping(path="/dobaviPosete")
    @PreAuthorize("hasRole('PACIJENT') or hasRole('FARMACEUT') or hasRole('DERMATOLOG')")
    public List<PosetaDTO> dobaviPosete(){
    	

    	return posetaService.dobaviPosete();
    }

    @GetMapping(path="/dobaviPoseteAktivne")
    //@PreAuthorize("hasAnyRole('FARMACEUT', 'DERMATOLOG')")
    public List<PosetaDTO> dobaviPoseteAktivne(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Radnik radnik = (Radnik)auth.getPrincipal();
        return this.posetaService.getAktivnePosete(radnik);
    }

    @PostMapping(value = "/traziZamenu")
    public Map<String, List<LijekDTO>> traziZamenu(@RequestBody Map<String, Object> params){
        return this.posetaService.traziZamenu(params);

    }

    @PostMapping(value="/proveriDostupnost")
    public Boolean proveriDostupnost(@RequestBody Map<String, Object> params){
        return this.posetaService.proveriDostupnost(params, stanjeLijekaRepository);
    }
    @GetMapping(path="/dobaviIstorijuD")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<PosetaDTO> dobaviIstorijuD(){
        return posetaService.dobaviIstorijuD();
    }

    @GetMapping(path="/dobaviIstorijuF")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<PosetaDTO> dobaviIstorijuF(){
        return posetaService.dobaviIstorijuF();
    }

    @PostMapping(value="/zakaziPosetu")
    public Boolean zakaziPosetu( @RequestBody Map<String, Object> podaci){

        return this.posetaService.kreirajPosetu(podaci);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/otkazi")
    @PreAuthorize("hasRole('PACIJENT')")
    public boolean izbPosetu( @RequestBody Map<String,Object> podaci){

        String podaci1=podaci+"";
        String[] deo1=podaci1.split(" ");
        String id=deo1[0].split("=")[2];
        String id2=id.replace(",", "");
        System.out.println("faca "+id);
        return this.posetaService.izbaciPosetuF(id2);//isto je izbaciti posetu i farmaceutu i dermatologu
    }
    @RequestMapping(method = RequestMethod.POST, value = "/otkaziF")
    @PreAuthorize("hasRole('PACIJENT')")
    public boolean izbPosetuF( @RequestBody Map<String,Object> podaci){
        System.out.println("micka se licka");
        System.out.println(podaci);
        String podaci1=podaci+"";
        String[] deo1=podaci1.split(" ");
        String id=deo1[0].split("=")[2];
        String id2=id.replace(",", "");
        System.out.println("faca "+id);
        return this.posetaService.izbaciPosetuF(id2);
    }

    @GetMapping(path="/dobaviSlobodnePoseteDermatologa")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<SlobodanTerminDTO> slobodnePoseteD(){
        return posetaService.slobodnePoseteD();
    }

    @PostMapping(path="/rezervisiPosetuD")
    @PreAuthorize("hasRole('PACIJENT')")
    public String rezervisiPosetuD( @RequestBody String id){

        return this.posetaService.zakaziPosetuD(id);
    }

    @PostMapping(path = "/nabaviSlobodneTermine")
    public List<SlobodanTerminDTO> nabaviSlobodneTermine(@RequestBody Map<String, Object> data){
        return posetaService.slobodniTermini(data);
    }

}
