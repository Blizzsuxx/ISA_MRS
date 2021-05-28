package mrsisa.projekat.poseta;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping(path="api/v1/posete")
public class PosetaController {
    private final PosetaService posetaService;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    @Autowired
    public PosetaController(PosetaService apotekaService,StanjeLijekaRepository stanjeLijekaRepository){
        this.posetaService = apotekaService;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
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
        return posetaService.dobaviPosetePacijenta("zarko");
    }
    @GetMapping("/dobaviPosetePacijentaF")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<PosetaDTO> dobaviPosetePacijentaF(){
        return posetaService.dobaviPosetePacijentaF("zarko");
    }

    @GetMapping(path="/dobaviPosete")
    @PreAuthorize("hasRole('PACIJENT') or hasRole('FARMACEUT') or hasRole('DERMATOLOG')")
    public List<PosetaDTO> dobaviPosete(){
    	

    	return posetaService.dobaviPosete();
    }

    @GetMapping(path="/dobaviPoseteAktivne")
    @PreAuthorize("hasAnyRole('DERMATOLOG', 'FARMACEUT')")
    public List<PosetaDTO> dobaviPoseteAktivne(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Radnik radnik = (Radnik)auth.getPrincipal();
        return this.posetaService.getAktivnePosete(radnik);
    }

    @PostMapping(value="/proveriDostupnost")
    public Boolean proveriDostupnost(@RequestBody Map<String, Object> params){


        if((Long)params.get("pregledID") == (null)){
            return false;
        }
        Poseta poseta = this.posetaService.findId((Long)params.get("pregledID"));

        List<Map<String, Object>> lekoviID = (List<Map<String, Object>>) params.get("lijekovi");
        for(Map<String, Object> lek : lekoviID){
            boolean lekPostojiUApoteci = false;
            for(StanjeLijeka stanjeLijeka : poseta.getApoteka().getLijekovi()){
                if(stanjeLijeka.getId().equals ((Long)lek.get("id"))){
                    lekPostojiUApoteci = true;
                    if(stanjeLijeka.getKolicina() < (int)lek.get("kolicina")){
                        return true;
                    }
                    else{
                        stanjeLijeka.setZatrazen(stanjeLijeka.getZatrazen()+1);
                        stanjeLijeka.setZatrazenDatum(LocalDateTime.now());
                        this.stanjeLijekaRepository.save(stanjeLijeka);
                    }
                    break;
                }
            }
            if(!lekPostojiUApoteci){
                return true;
            }
        }

        return false;
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
    public void zakaziPosetu( @RequestBody Map<String, Object> podaci){

        this.posetaService.kreirajPosetu(podaci);
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
    public List<PosetaDTO> slobodnePoseteD(){
        return posetaService.slobodnePoseteD();
    }

    @PostMapping(path="/rezervisiPosetuD")
    @PreAuthorize("hasRole('PACIJENT')")
    public String rezervisiPosetuD( @RequestBody String id){

        return this.posetaService.zakaziPosetuD(id);
    }

}
