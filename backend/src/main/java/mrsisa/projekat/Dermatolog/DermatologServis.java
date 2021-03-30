package mrsisa.projekat.Dermatolog;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologServis {


    private final DermatologRepository dermatologRepository;

    @Autowired
    public DermatologServis(DermatologRepository dermatologRepository){
        this.dermatologRepository = dermatologRepository;
    }

    public List<Dermatolog> dobaviDermatologe(){


        return new ArrayList<Dermatolog>();
    }

}
