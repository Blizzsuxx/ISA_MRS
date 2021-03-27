package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StanjeLijekaService {
    private final StanjeLijekaRepository stanjeLijekaRepository;

    @Autowired
    public StanjeLijekaService(StanjeLijekaRepository stanjeLijekaRepository){
        this.stanjeLijekaRepository = stanjeLijekaRepository;
    }
    public List<Long> promjenaStatusaProdaje(List<Long> identifikatori){
        List<Long> zabranjeniIdentifikatori = new ArrayList<Long>();
        List<StanjeLijeka> stanjeLijekova = stanjeLijekaRepository.findAllById(identifikatori);
        Lijek privremeniLijek =  null;
        Apoteka privremenaApoteka;
        boolean rezervisan;
        for (StanjeLijeka stanje: stanjeLijekova)
        {
            if(!stanje.isProdaja()) {
                stanje.setProdaja(!stanje.isProdaja());
                this.stanjeLijekaRepository.save(stanje);
            }
            else {
                rezervisan = false;
                privremeniLijek = stanje.getLijek();
                privremenaApoteka = stanje.getApoteka();
                for(Rezervacija rez : privremenaApoteka.getRezervacije()){
                    for(StanjeLijeka sl : rez.getRezervisaniLijekovi()){
                        if(sl.getLijek().getId().equals(privremeniLijek.getId())){
                            rezervisan = true;
                        }
                    }
                }
                if(rezervisan){
                    zabranjeniIdentifikatori.add(stanje.getId());
                }
                else{
                    stanje.setProdaja(!stanje.isProdaja());
                    this.stanjeLijekaRepository.save(stanje);
                }
            }

        }
    return zabranjeniIdentifikatori;
    }
}
