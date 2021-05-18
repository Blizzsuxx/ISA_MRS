package mrsisa.projekat.akcija;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AkcijaService {
    private final AkcijaRepository akcijaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    @Autowired
    public AkcijaService(AkcijaRepository akcijaRepository,StanjeLijekaRepository stanjeLijekaRepository){
        this.akcijaRepository = akcijaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
    }
    public List<AkcijaDTO> vratiAkcije() {
        StanjeLijekaDTO sd=new StanjeLijekaDTO();
//        AkcijaDTO dto=new AkcijaDTO("akcija Bromazepana 20%",sd,1L, LocalDate.now());
//        AkcijaDTO dto1=new AkcijaDTO("akcija Brufena 20%",sd,2L, LocalDate.now());
//        AkcijaDTO dto2=new AkcijaDTO("akcija Baralgina 30%",sd,3L, LocalDate.now());
        List<AkcijaDTO> lista=new ArrayList<>();
//        lista.add(dto);lista.add(dto1);lista.add(dto2);
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
