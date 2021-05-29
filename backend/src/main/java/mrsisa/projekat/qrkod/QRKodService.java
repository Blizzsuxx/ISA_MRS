package mrsisa.projekat.qrkod;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptRepository;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QRKodService {
    private final ApotekaRepository apotekaRepository;
    private final EreceptRepository ereceptRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final PacijentRepository pacijentRepository;

    @Autowired
    public QRKodService(ApotekaRepository apotekaRepository,
                        EreceptRepository ereceptRepository,
                        StanjeLijekaRepository stanjeLijekaRepository,
                        PacijentRepository pacijentRepository){
        this.apotekaRepository = apotekaRepository;
        this.ereceptRepository = ereceptRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.pacijentRepository = pacijentRepository;
    }

    @Transactional
    public boolean kreirajErecept(ApotekaDTO apotekaDTO, Pacijent p ){
        String[] lista = apotekaDTO.getRezultat().split(",");
        Apoteka a = this.apotekaRepository.findOneById(apotekaDTO.getId());
        Erecept e = new Erecept();
        e.setPacijent(this.pacijentRepository.findByUsername(p.getUsername()));
        e.setSifra("sifra");
        e.setDatumIzdavanja(LocalDateTime.now());

        for (String stringic: lista){
            String[] par = stringic.split(":");
            for (StanjeLijeka sl: a.getLijekovi()){
                if (sl.getLijek().getId()==Long.parseLong(par[0])){
                    sl.setKolicina(sl.getKolicina() - Integer.parseInt(par[1]));
                    sl.seteRecept(e);
                    this.stanjeLijekaRepository.save(sl);
                    break;
                }
            }
        }

        this.ereceptRepository.save(e);
        return true;
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
                apotekeDTO.add(new ApotekaDTO(apoteka, ukupnaCijena, kod));
        }

        return apotekeDTO;
    }
}
