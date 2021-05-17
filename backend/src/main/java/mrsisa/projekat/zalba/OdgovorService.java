package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdgovorService {

    private final OdgovorRepository odgovorRepository;

    @Autowired
    public OdgovorService(OdgovorRepository odgovorRepository){
        this.odgovorRepository = odgovorRepository;
    }

    public void save(Odgovor o){
        this.odgovorRepository.save(o);
    }
}
