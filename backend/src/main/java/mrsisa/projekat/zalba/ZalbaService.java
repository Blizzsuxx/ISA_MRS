package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ZalbaService {

    private ZalbaRepository zalbaRepository;

    @Autowired
    public ZalbaService(ZalbaRepository zalbaRepository){
        this.zalbaRepository = zalbaRepository;
    }

    public void save(Zalba z){
        this.zalbaRepository.save(z);
    }

    @Transactional
    public List<Zalba> findAllByPacijent(String pacijent){
        return this.zalbaRepository.findAllByPacijent(pacijent);
    }
}
