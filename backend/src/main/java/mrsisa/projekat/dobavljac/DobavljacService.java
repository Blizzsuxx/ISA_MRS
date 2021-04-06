package mrsisa.projekat.dobavljac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
