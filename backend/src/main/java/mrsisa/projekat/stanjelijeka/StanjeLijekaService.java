package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.istorijaPromjena.IstorijaPromjena;
import mrsisa.projekat.istorijaPromjena.IstorijaPromjenaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StanjeLijekaService {
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final RezervacijaRepository rezervacijeRepository;
    private final IstorijaPromjenaRepository istorijaPromjenaRepository;

    @Autowired
    public StanjeLijekaService(StanjeLijekaRepository stanjeLijekaRepository, RezervacijaRepository rezervacijaRepository,
                               IstorijaPromjenaRepository istorijaPromjenaRepository){
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.rezervacijeRepository = rezervacijaRepository;
        this.istorijaPromjenaRepository =  istorijaPromjenaRepository;
    }
    @Transactional
    public boolean promjeniCijenu(Long id,double cijena,String datum,Long apoteka_id){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] dijelovi = datum.split("T");
        String noviDatum = (dijelovi[0]+" "+dijelovi[1]).split("\\.")[0];

        LocalDateTime datum1 = LocalDateTime.parse(noviDatum,format);
        StanjeLijeka stanjeLijeka = stanjeLijekaRepository.findById(id).orElse(null);
        if(stanjeLijeka!=null){
            for(IstorijaPromjena istorijaPromjena: istorijaPromjenaRepository.findAll()){
                    if(istorijaPromjena.getDatumOd().equals(stanjeLijeka.getDatumIstekaCijene()) &&
                        istorijaPromjena.getApoteka().getId().equals(stanjeLijeka.getApoteka().getId())
                        && istorijaPromjena.getStanjeLijeka().getId().equals(stanjeLijeka.getId())){
                        istorijaPromjena.setDatumDo(datum1);
                    }
            }
            IstorijaPromjena istorijaPromjena =  new IstorijaPromjena(stanjeLijeka.getApoteka(),stanjeLijeka);
            istorijaPromjena.setDatumOd(datum1);
            System.out.println("Ovde je doslo i ovde je proslo");
            this.istorijaPromjenaRepository.save(istorijaPromjena);
            if(stanjeLijeka.getApoteka().getId().equals(apoteka_id)){
            stanjeLijeka.setCijena(cijena);
            stanjeLijeka.setDatumIstekaCijene(datum1);
            stanjeLijekaRepository.save(stanjeLijeka);
            return false;
            }

        }
        return true;
    }


    public int izbrisiLijekove(Long identifikatori,Long apoteka_id) {
        StanjeLijeka zaBrisanje  =  this.stanjeLijekaRepository.findById(identifikatori).orElse(null);
        if(zaBrisanje!=null){
            for(Rezervacija rezervacija: this.rezervacijeRepository.findAll()){
                if(rezervacija.getApoteka().getId().equals(apoteka_id)){
                    for(StanjeLijeka stanjeLijeka: rezervacija.getRezervisaniLijekovi()){
                        if(stanjeLijeka.getId().equals(identifikatori)){
                            return 1;
                        }
                    }
                }
            }
        }
        this.stanjeLijekaRepository.delete(zaBrisanje);
        return 0;
    }

    public List<StanjeLijeka> dobaviStanjaLijekova(Long id) {
        return stanjeLijekaRepository.nadjiStanja(id);
    }

    @Transactional
    public List<StanjeLijekaDTO> dobaviZatrazene(AdministratorApoteke adminApoteke) {
        List<StanjeLijekaDTO> stanjaLijekova =  new ArrayList<>();
        StanjeLijekaDTO temp ;
        for(StanjeLijeka stanjeLijeka: this.stanjeLijekaRepository.findAll()){
            if(stanjeLijeka.getApoteka()==null)
                    continue;
            System.out.println("--------AAA------");
            System.out.println(stanjeLijeka.getApoteka().getId());
            System.out.println(adminApoteke.getApoteka().getId());
            System.out.println(stanjeLijeka.getZatrazen());
            System.out.println("---------AAA------");
            if(stanjeLijeka.getApoteka().getId().equals(adminApoteke.getApoteka().getId()) && stanjeLijeka.getZatrazen()>0){
                System.out.println("Usao je ovde");
                temp = new StanjeLijekaDTO(stanjeLijeka);
                temp.setImeApoteke(stanjeLijeka.getApoteka().getIme());
                stanjaLijekova.add(temp);
            }
        }
        return stanjaLijekova;
    }
    public void ocistiZatrazeni(Long id) {
        StanjeLijeka stanjeLijeka =  this.stanjeLijekaRepository.findById(id).orElse(null);
        if(stanjeLijeka!=null){
            stanjeLijeka.setZatrazen(0);
            this.stanjeLijekaRepository.save(stanjeLijeka);
        }
    }
}
