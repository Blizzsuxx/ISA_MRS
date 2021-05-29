package mrsisa.projekat.qrkod;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class QRKodService {
    private final ApotekaRepository apotekaRepository;

    @Autowired
    public QRKodService(ApotekaRepository apotekaRepository){
        this.apotekaRepository = apotekaRepository;
    }

    @Transactional
    public List<ApotekaDTO> vratiApotekeNaOsnovuKoda(String kod){
        List<ApotekaDTO> apotekeDTO = new ArrayList<>();
        String[] nizId = kod.split(",");
        for (Apoteka apoteka: this.apotekaRepository.findAll()){
            int brojac = 0;
            double ukupnaCijena = 0;
            for (StanjeLijeka sl: apoteka.getLijekovi()){
                for (String stringic: nizId){
                    String[] par = stringic.split(":");
                    if (sl.getLijek().getId().equals(Long.parseLong(par[0])) && sl.getKolicina() >= Integer.parseInt(par[1])){
                        ++brojac;
                        ukupnaCijena += sl.getCijena()*Integer.parseInt(par[1]);
                        break;
                    }
                }
            }
            if (brojac == nizId.length)
                apotekeDTO.add(new ApotekaDTO(apoteka, ukupnaCijena));
        }

        return apotekeDTO;
    }
}
