package mrsisa.projekat.radnoVrijeme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadnoVrijemeService {
    private final RadnoVrijemeRepository radnoVrijemeRepository;
    @Autowired
    public RadnoVrijemeService(RadnoVrijemeRepository radnoVrijemeRepository){
        this.radnoVrijemeRepository = radnoVrijemeRepository;
    }
}
