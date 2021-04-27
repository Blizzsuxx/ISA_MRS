package mrsisa.projekat.radnoVrijeme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RadnoVrijemeService {
    private final RadnoVrijemeRepository radnoVrijemeRepository;
    @Autowired
    public RadnoVrijemeService(RadnoVrijemeRepository radnoVrijemeRepository){
        this.radnoVrijemeRepository = radnoVrijemeRepository;
    }
    @Transactional
    public RadnoVrijemeDTO dobaviRadnoVrijeme(Integer id) {
        for (RadnoVrijeme radnoVrijeme : this.radnoVrijemeRepository.findAll())
        {
            System.out.println(radnoVrijeme.getDermatolog().getId());
            System.out.println(id);
            if(radnoVrijeme.getDermatolog().getId().equals(id)){
                return new RadnoVrijemeDTO(radnoVrijeme);
            }
        }
        return null;
    }
}
