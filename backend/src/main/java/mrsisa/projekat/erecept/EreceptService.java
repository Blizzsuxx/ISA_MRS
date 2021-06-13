package mrsisa.projekat.erecept;

import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaService;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EreceptService {

    private final EreceptRepository ereceptRepository;

    @Autowired
    public EreceptService(EreceptRepository ereceptRepository) {
        this.ereceptRepository = ereceptRepository;
    }

    @Transactional
    public void postaviErecept(Map<String, Object> podaci, PosetaService posetaService) {

        Long pregledID = Long.parseLong(podaci.get("pregledID").toString());
        Poseta poseta = posetaService.findId(pregledID);
        Erecept erecept = new Erecept();
        erecept.setDatumIzdavanja(LocalDateTime.now());
        erecept.setPacijent(poseta.getPacijent());
        erecept.setPoseta(poseta);
        erecept.setSifra("sifra");
        poseta.setOpis((String) podaci.get("zapisano"));
        erecept.setPrepisaniLijekovi( new ArrayList<>());
        List<Map<String, Object>> lijekovi = (List<Map<String, Object>>) podaci.get("lijekovi");

        for(Map<String, Object> lek: lijekovi){
            Long id = Long.parseLong(lek.get("id").toString());
            for(StanjeLijeka lekIzApoteke : poseta.getApoteka().getLijekovi()){
                if(lekIzApoteke.getId().equals(id)){
                    erecept.getPrepisaniLijekovi().add(lekIzApoteke);
                    break;
                }
            }
        }

        ereceptRepository.save(erecept);
    }
}
