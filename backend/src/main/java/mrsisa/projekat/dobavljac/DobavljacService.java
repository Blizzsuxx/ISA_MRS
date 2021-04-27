package mrsisa.projekat.dobavljac;

import mrsisa.projekat.dermatolog.Dermatolog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DobavljacService {
    private final DobavljacRepository dobavljacRepository;

    @Autowired
    public DobavljacService(DobavljacRepository dobavljacRepository){
        this.dobavljacRepository = dobavljacRepository;
    }

    public Dobavljac save(Dobavljac d){
        return this.dobavljacRepository.save(d);
    }

    public List<Dobavljac> findAll(){
        return dobavljacRepository.findAll();
    }

    public Page<Dobavljac> findAll(Pageable page){
        return dobavljacRepository.findAll(page);
    }
}
