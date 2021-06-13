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
        {   if(radnoVrijeme.getDermatolog()!=null){
                if(radnoVrijeme.getDermatolog().getId().equals(id)){
                    return new RadnoVrijemeDTO(radnoVrijeme);
            }}
        }

        for (RadnoVrijeme radnoVrijeme : this.radnoVrijemeRepository.findAll())
        {
            if(radnoVrijeme.getFarmaceuet()!=null){
                if(radnoVrijeme.getFarmaceuet().getId().equals(id)){
                    return new RadnoVrijemeDTO(radnoVrijeme);
                }}
        }
        return null;
    }
}
