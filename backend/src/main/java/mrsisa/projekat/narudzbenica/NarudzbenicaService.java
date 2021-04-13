package mrsisa.projekat.narudzbenica;


import com.fasterxml.jackson.databind.ObjectMapper;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NarudzbenicaService {

    private final  NarudzbenicaRepository narudzbenicaRepository;
    private final ApotekaRepository apotekaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    @Autowired
    public NarudzbenicaService(NarudzbenicaRepository narudzbenicaRepository, ApotekaRepository apotekaRepository,
                               StanjeLijekaRepository stanjeLijekaRepository){
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.apotekaRepository = apotekaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
    }
    @Transactional
    public void kreirajNarudzbenicu( @RequestBody Map<String, Object> podaci) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ObjectMapper mapper = new ObjectMapper();
        List<StanjeLijeka> stanjeLijekova = (ArrayList<StanjeLijeka>) podaci.get("lijekovi");
        LocalDateTime datum =  LocalDateTime.parse((String)podaci.get("datum"),format);
        Long idApoteka = Long.valueOf((Integer) podaci.get("apoteka"));
        Apoteka apoteka = this.apotekaRepository.findById(idApoteka).orElse(null);
        Narudzbenica narudzbenica =  new Narudzbenica();
        narudzbenica.setApoteka(apoteka);
        narudzbenica.setRok(datum);
        StanjeLijeka temp;
        for (Object object:(ArrayList<StanjeLijeka>) podaci.get("lijekovi")
             ) {
            temp  = mapper.convertValue(object,StanjeLijeka.class);
            for(StanjeLijeka stanjelijeka: apoteka.getLijekovi()) {
                if (temp.getId().equals(stanjelijeka.getId())) {
                    stanjelijeka.setKolicina(stanjelijeka.getKolicina() - temp.getKolicina());
                    this.stanjeLijekaRepository.save(stanjelijeka);
                }
            }
            System.out.println(temp.getApoteka());
            temp.setApoteka(null);
            temp.setNarudzbenica(narudzbenica);
            temp.setId(null);
            this.stanjeLijekaRepository.save(temp);
        }
        this.narudzbenicaRepository.save(narudzbenica);

    }
}
