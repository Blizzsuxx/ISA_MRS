package mrsisa.projekat.apoteka;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApotekaService {
    private final ApotekaRepository apotekaRepository;

    @Autowired
    public ApotekaService(ApotekaRepository apotekaRepository){
        this.apotekaRepository = apotekaRepository;
    }

    public Apoteka save(Apoteka a){
        return this.apotekaRepository.save(a);
    }

    @Transactional
    public List<StanjeLijeka> dobaviStanjaLijekova(Long id) {
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        if(apoteka== null)
            return new ArrayList<StanjeLijeka>();
        List<StanjeLijekaDTO> povratna_stanja  =  new ArrayList<StanjeLijekaDTO>();
        for (StanjeLijeka sl:apoteka.getLijekovi())
        {
            povratna_stanja.add(new StanjeLijekaDTO(sl));
        }
        return apoteka.getLijekovi();
    }
    public List<Apoteka> dobaviApoteke(){

    	List<Apoteka> apoteke=List.of(
    			new Apoteka(1L,"Apoteka1",null),new Apoteka(2L,"Apoteka2",null),new Apoteka(3L,"Apoteka3",null),new Apoteka(4L,"Apoteka4",null));
    	Adresa ad=new Adresa("Kazahstan","ptt","Ulica","5",50, 50);
    	for(Apoteka a : apoteke) {
    		a.setAdresa(ad);
    	}
        //ovde treba ucitati apoteke iz baze
    	//return apotekaRepository.findAll();
        return apoteke;
    }
    @Transactional
    public ApotekaDTO dobaviApoteku(Long id) {
        ApotekaDTO  dto =  new ApotekaDTO(apotekaRepository.findById(id).orElse(null));
        
        return dto;
    }
}
