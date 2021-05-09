package mrsisa.projekat.akcija;

import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AkcijaService {

    public List<AkcijaDTO> vratiAkcije() {
        StanjeLijekaDTO sd=new StanjeLijekaDTO();
        AkcijaDTO dto=new AkcijaDTO("akcija Bromazepana 20%",sd,1L, LocalDate.now());
        AkcijaDTO dto1=new AkcijaDTO("akcija Brufena 20%",sd,2L, LocalDate.now());
        AkcijaDTO dto2=new AkcijaDTO("akcija Baralgina 30%",sd,3L, LocalDate.now());
        List<AkcijaDTO> lista=new ArrayList<>();
        lista.add(dto);lista.add(dto1);lista.add(dto2);
        return lista;
    }
}
