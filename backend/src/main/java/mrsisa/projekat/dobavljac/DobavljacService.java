package mrsisa.projekat.dobavljac;

import mrsisa.projekat.dermatolog.DermatologRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DobavljacService {
    private final DobavljacRepository dobavljacRepository;

    @Autowired
    public DobavljacService(DobavljacRepository dobavljacRepository){
        this.dobavljacRepository = dobavljacRepository;
    }
}
