package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LijekService {
    private final LijekRepository lijekRepository;

    @Autowired
    public LijekService(LijekRepository lijekRepository){
        this.lijekRepository = lijekRepository;
    }

    public Lijek save(Lijek l){
        return this.lijekRepository.save(l);
    }

    public List<Lijek> dobaviSveLijekove(){
        return List.of(new Lijek(
                1L,
                "andija",
                "andija",
                "andija",
                "andija",
                "andija",
                "andija"
        ));

    }

    public List<LijekDTO> dobaviSveDTOLijekove(){
        return List.of(new LijekDTO(
                    1L,
                    "marko",
                    "marko",
                    10.0
                ),
                new LijekDTO(
                        2L,
                        "andrija",
                        "andrija",
                        5
                ),
                new LijekDTO(
                        3L,
                        "dragan",
                        "dragan",
                        6
                ),
                new LijekDTO(
                        4L,
                        "natasa",
                        "natasa",
                        7
                )
        );
    }

}
