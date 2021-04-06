package mrsisa.projekat.dermatolog;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologService {
    private final DermatologRepository dermatologRepository;

    @Autowired
    public DermatologService(DermatologRepository dermatologRepository){
        this.dermatologRepository = dermatologRepository;
    }

    public List<Dermatolog> dobaviDermatologe(){


        return new ArrayList<Dermatolog>();
    }

    public Dermatolog save(Dermatolog d){
        return this.dermatologRepository.save(d);
    }

    public List<Dermatolog> findAll(){
        return dermatologRepository.findAll();
    }

    public Page<Dermatolog> findAll(Pageable page){
        return dermatologRepository.findAll(page);
    }
}
