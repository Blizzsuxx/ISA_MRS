package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void promjeniCijenu(Long id,double cijena,String datum){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] dijelovi = datum.split("T");
        String noviDatum = (dijelovi[0]+" "+dijelovi[1]).split("\\.")[0];

        LocalDateTime datum1 = LocalDateTime.parse(noviDatum,format);
        StanjeLijeka stanjeLijeka = stanjeLijekaRepository.findById(id).orElse(null);
        if(stanjeLijeka!=null){
            stanjeLijeka.setCijena(cijena);
            stanjeLijeka.setDatumIstekaCijene(datum1);
            stanjeLijekaRepository.save(stanjeLijeka);
        }

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

    public void izbrisiLijekove(List<Long> identifikatori) {
        List<StanjeLijeka> zaBrisanje  =  this.stanjeLijekaRepository.findAllById(identifikatori);
        this.stanjeLijekaRepository.deleteAll(zaBrisanje);
    }

    public List<StanjeLijeka> dobaviStanjaLijekova(Long id) {
        return stanjeLijekaRepository.nadjiStanja(id);
    }
}
