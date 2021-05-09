package mrsisa.projekat.apoteka;


import com.sun.net.httpserver.HttpServer;
import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.adresa.AdresaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
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
    @Autowired
    public ApotekaService(ApotekaRepository apotekaRepository,AdresaRepository adresaRepository){
        this.apotekaRepository = apotekaRepository;
        this.adresaRepository = adresaRepository;
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
    public List<Apoteka> dobaviApoteke(){

    	List<Apoteka> apoteke=List.of(
    			new Apoteka(1L,"Apoteka1",null),new Apoteka(2L,"Apoteka2",null),new Apoteka(3L,"Apoteka3",null),new Apoteka(4L,"Apoteka4",null));
    	Adresa ad=new Adresa("Kazahstan","ptt","Ulica","5",50, 50);
    	for(Apoteka a : apoteke) {
    		a.setAdresa(ad);
    	}
    	return apoteke;


    }
    @Transactional
    public ApotekaDTO dobaviApoteku(Long id) {
        ApotekaDTO dto =  new ApotekaDTO(apotekaRepository.findById(id).orElse(null));
        
        return dto;
    }
    @Transactional
    public List<LijekDTO> dobaviLijekoveAlergija(Long id) {
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        List<StanjeLijeka> stanja = apoteka.getLijekovi();
        ArrayList<LijekDTO> bezAlergija = new ArrayList<>();
        if (stanja.isEmpty() == false) {
            for (StanjeLijeka st : stanja) {
                bezAlergija.add(new LijekDTO(st.getLijek()));
            }
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
}
