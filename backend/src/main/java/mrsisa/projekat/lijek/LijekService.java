package mrsisa.projekat.lijek;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LijekService {
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

}
