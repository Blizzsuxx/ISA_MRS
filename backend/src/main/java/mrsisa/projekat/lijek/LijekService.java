package mrsisa.projekat.lijek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Lijek findByNaziv(String naziv){
        return this.lijekRepository.findByNaziv(naziv);
    }

    public List<Lijek> findAll(){
        return this.lijekRepository.findAll();
    }

    public Page<Lijek> findAll(Pageable page){
        return this.lijekRepository.findAll(page);
    }

    public void remove(String naziv){
        this.lijekRepository.deleteByNaziv(naziv);
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
                    10.0,
                    "marko",
                    "marko"
                ),
                new LijekDTO(
                        2L,
                        "andrija",
                        "andrija",
                        5,
                        "marko",
                        "marko"
                ),
                new LijekDTO(
                        3L,
                        "dragan",
                        "dragan",
                        6,
                        "marko",
                        "marko"
                ),
                new LijekDTO(
                        4L,
                        "natasa",
                        "natasa",
                        7,
                        "marko",
                        "marko"
                )
        );
    }

}
