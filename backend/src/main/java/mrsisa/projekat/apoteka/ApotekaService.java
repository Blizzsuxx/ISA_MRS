package mrsisa.projekat.apoteka;


import com.sun.net.httpserver.HttpServer;
import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.adresa.AdresaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApotekaService {
    private final ApotekaRepository apotekaRepository;
    private final AdresaRepository adresaRepository;
    private final LijekRepository lekRepository;
    private final PacijentRepository pacijentRepository;
    @Autowired
    public ApotekaService(ApotekaRepository apotekaRepository,AdresaRepository adresaRepository, LijekRepository l, PacijentRepository p){
        this.apotekaRepository = apotekaRepository;
        this.adresaRepository = adresaRepository;
        this.lekRepository=l;
        this.pacijentRepository=p;
    }

    public Apoteka save(Apoteka a){
        return this.apotekaRepository.save(a);
    }
    public List<Apoteka> findAll(){
        return this.apotekaRepository.findAll();
    }

    @Transactional
    public List<StanjeLijekaDTO> dobaviStanjaLijekova(Long id) {
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        if(apoteka== null)
            return new ArrayList<StanjeLijekaDTO>();
        List<StanjeLijekaDTO> povratna_stanja  =  new ArrayList<StanjeLijekaDTO>();
        for (StanjeLijeka sl:apoteka.getLijekovi())
        {
            povratna_stanja.add(new StanjeLijekaDTO(sl));
        }
        return povratna_stanja;
    }
    @Transactional
    public List<ApotekaDTO> dobaviApoteke(){
        List<ApotekaDTO> apoteke = new ArrayList<>();
        ApotekaDTO temp ;
    	for(Apoteka apoteka: this.apotekaRepository.findAll()){
    	    temp =  new ApotekaDTO(apoteka.getId(),apoteka.getIme());
    	    temp.setAdresa(apoteka.getAdresa());
    	    apoteke.add(temp);
        }
    	return apoteke;
    }

    @Transactional
    public ApotekaDTO dobaviApoteku(Long id) {
        ApotekaDTO dto =  new ApotekaDTO(apotekaRepository.findById(id).orElse(null));
        
        return dto;
    }
    @Transactional
    public List<LijekDTO> dobaviLijekoveAlergija(Long id) { //TODO dobiti id osobe za oduzimanje lekova
        List<Lijek> sviLekovi= this.lekRepository.findAll();
        Pacijent pacijent= this.pacijentRepository.findOneById(9);
        List<LijekDTO> bezAlergija =new ArrayList<>();int a=0;
        for(Lijek l : sviLekovi) {
         a=0;
            for(Lijek lp : pacijent.getAlergije()){
                if(lp.getId()==l.getId()){
                    a=1;
                    break;
                }
            }if(a==0){
            bezAlergija.add(new LijekDTO(l));}
        }
        return bezAlergija;
    }
    @Transactional
    public ApotekaDTO dobaviApotekuAdmin(AdministratorApoteke adminApoteke) {
        Apoteka apoteka =  adminApoteke.getApoteka();
        apoteka = apotekaRepository.findById(apoteka.getId()).orElse(null);

        if(apoteka==null)
            return new ApotekaDTO();
        else{
            ApotekaDTO apotekaDTO =  new ApotekaDTO();
            apotekaDTO.setIme(apoteka.getIme());
            apotekaDTO.setId(apoteka.getId());
            apotekaDTO.setAdresa(apoteka.getAdresa());
            return apotekaDTO;
        }

    }
    @Transactional
    public void azurirajApotekuAdmin(AdministratorApoteke adminApoteke, ApotekaDTO apotekaDTO) {
        Apoteka apoteka =  adminApoteke.getApoteka();
        apoteka = apotekaRepository.findById(apoteka.getId()).orElse(null);
        System.out.println(apoteka.getIme());
        apoteka.setIme(apotekaDTO.getIme());
        System.out.println(apoteka.getIme());
        apoteka.postaviAdresuIzDTO(apotekaDTO);
        adresaRepository.save(apoteka.getAdresa());
        apotekaRepository.save(apoteka);
    }

    public List<StanjeLijekaDTO> dobaviSveDostupneLijekove() {
        //ovo iz svih apoteka vraca sve lekove, tj dto
        Lijek l1=new Lijek(
                3L,
                "Lekadol",
                "Protiv boli",
                "tableta",
                "ljiek",
                "Biofarm",
                "Lijek"
        );
        Lijek l3=new Lijek(
                4L,
                "Paracetamol",
                "Protiv bolova",
                "tableta",
                "ljiek",
                "Biofarm",
                "Lijek"
        );
        Lijek l4=new Lijek(
                2L,
                "Brufen",
                "Protiv boli",
                "tableta",
                "ljiek",
                "Biofarm",
                "Lijek"
        );
        LijekDTO d1=new LijekDTO(l1); LijekDTO d2=new LijekDTO(l3); LijekDTO d3=new LijekDTO(l4);
        StanjeLijekaDTO sd1=new StanjeLijekaDTO(1L, d1,4, true, 500, "21.4.2021.", "apoteka1");
        StanjeLijekaDTO sd2=new StanjeLijekaDTO(2L, d2,3, true, 500, "21.4.2021.", "apoteka2");
        StanjeLijekaDTO sd3=new StanjeLijekaDTO(3L, d3,1, true, 500, "21.4.2021.", "apoteka1");
        List<StanjeLijekaDTO> stanja=new ArrayList<>();
        stanja.add(sd1);stanja.add(sd2); stanja.add(sd3);

        return stanja;
    }

    public void rezervisiLek(String lek) {
        List<StanjeLijekaDTO> stanjaLekova=dobaviSveDostupneLijekove();
        for(StanjeLijekaDTO s : stanjaLekova){
            if(s.getId().toString().equals(lek)){
                s.setKolicina(s.getKolicina()-1);
                break;
            }
        }
    }

    public void sacuvajApoteku(ApotekaDTO dummy) {
        Apoteka apoteka = new Apoteka(dummy);
        Adresa adresa =  new Adresa(dummy.getMjesto(),dummy.getPtt(), dummy.getUlica(), dummy.getBroj(),dummy.getDuzina(), dummy.getSirina());
        //this.adresaRepository.save(adresa);
        apoteka.setAdresa(adresa);
        this.apotekaRepository.save(apoteka);


    }
}
