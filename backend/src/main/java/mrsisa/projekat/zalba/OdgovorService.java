package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdgovorService {

    private final OdgovorRepository odgovorRepository;
    private final ZalbaRepository zalbaRepository;

    @Autowired
    public OdgovorService(OdgovorRepository odgovorRepository, ZalbaRepository zalbaRepository){
        this.odgovorRepository = odgovorRepository;
        this.zalbaRepository = zalbaRepository;
    }

    public void save(Odgovor o){
        this.odgovorRepository.save(o);
    }

    public List<Odgovor> findAllByZalba(int id) {
        Zalba z = this.zalbaRepository.findById(id)
                .orElse(null);
        return this.odgovorRepository.findAllByZalba(z);
    }

    public void sacuvaj(OdgovorDTO o){
        Zalba z = this.zalbaRepository.findById(o.getId()).orElse(null);
        Odgovor odgovor = new Odgovor(o);
        odgovor.setZalba(z);
        assert z != null;
        z.getOdgovori().add(odgovor);
        this.odgovorRepository.save(odgovor);
        this.zalbaRepository.save(z);

    }
}
