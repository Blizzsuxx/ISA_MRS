package mrsisa.projekat.poseta;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PosetaService {
    private final PosetaRepository posetaRepository;

    @Autowired
    public PosetaService(PosetaRepository apotekaRepository){
        this.posetaRepository = apotekaRepository;
    }

    public List<Poseta> dobaviPosete(Long id) {
        Apoteka apoteka = null;
        if(apoteka== null)
            return new ArrayList<Poseta>();
        return null;
    }
    public List<Poseta> dobaviPosete(){



        //ovde treba ucitati apoteke iz baze
    	//return apotekaRepository.findAll();
        return List.of(null);
    }
}
