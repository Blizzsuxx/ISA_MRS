package mrsisa.projekat.akcija;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AkcijaService {
    private final AkcijaRepository akcijaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final ApotekaRepository apotekaRepository;
    @Autowired
    public AkcijaService(AkcijaRepository akcijaRepository,StanjeLijekaRepository stanjeLijekaRepository, ApotekaRepository apotekaRepository){
        this.akcijaRepository = akcijaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.apotekaRepository=apotekaRepository;
    }
    @Transactional
    public List<AkcijaDTO> vratiAkcije(String id) {

        Apoteka apoteka=this.apotekaRepository.findOneById(Long.parseLong(id.trim()));
        List<AkcijaDTO> lista=new ArrayList<>();
        for(StanjeLijeka s : apoteka.getLijekovi()) {
            if (s.getAkcija() != null) {
                if ((s.getAkcija().getDatumOd().isEqual(LocalDateTime.now()) || s.getAkcija().getDatumOd().isBefore(LocalDateTime.now())) &&
                        (s.getAkcija().getDatumDo().isEqual(LocalDateTime.now()) || s.getAkcija().getDatumDo().isAfter(LocalDateTime.now()))
                ) {
                    lista.add(new AkcijaDTO(s.getAkcija(), s));

                }
            }
        }
        return lista;
    }

    public void kreirajAkciju(Map<String, Object> podaci) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datumOd = LocalDateTime.parse((String) podaci.get("datumOd"), format);
        LocalDateTime datumDo = LocalDateTime.parse((String) podaci.get("datumDo"), format);
        Long idStanjaLijeka = Long.valueOf((Integer) podaci.get("id"));
        Integer popust = (Integer) podaci.get("popust");
        StanjeLijeka stanjeLijeka =  this.stanjeLijekaRepository.findById(idStanjaLijeka).orElse(null);
        if(stanjeLijeka!=null){
            Akcija akcija =  new Akcija(popust,datumOd,datumDo);
            this.akcijaRepository.save(akcija);
            stanjeLijeka.setAkcija(akcija);
            this.stanjeLijekaRepository.save(stanjeLijeka);
        }


    }
}
