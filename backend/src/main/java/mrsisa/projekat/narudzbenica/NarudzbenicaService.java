package mrsisa.projekat.narudzbenica;


import com.fasterxml.jackson.databind.ObjectMapper;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
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
    private final LijekRepository lijekRepository;
    @Autowired
    public NarudzbenicaService(NarudzbenicaRepository narudzbenicaRepository, ApotekaRepository apotekaRepository,
                               StanjeLijekaRepository stanjeLijekaRepository,LijekRepository lijekRepository){
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.apotekaRepository = apotekaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.lijekRepository = lijekRepository;
    }
    @Transactional
    public void kreirajNarudzbenicu( @RequestBody Map<String, Object> podaci) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ObjectMapper mapper = new ObjectMapper();
        LocalDateTime datum =  LocalDateTime.parse((String)podaci.get("datum"),format);
        Long idApoteka = Long.valueOf((Integer) podaci.get("apoteka"));
        Apoteka apoteka = this.apotekaRepository.findById(idApoteka).orElse(null);
        Narudzbenica narudzbenica =  new Narudzbenica();
        narudzbenica.setApoteka(apoteka);
        narudzbenica.setRok(datum);
        LijekDTO temp;
        StanjeLijeka tempStanje;
        Lijek tempLijek;
        for (Object object:(ArrayList<LijekDTO>) podaci.get("lijekovi")
             ) {
            tempStanje = new StanjeLijeka();
            temp  = mapper.convertValue(object,LijekDTO.class);
            tempLijek = this.lijekRepository.findById(temp.getId()).orElse(null);
            tempStanje.setLijek(tempLijek);
            tempStanje.setKolicina(temp.getKolicina());
            tempStanje.setNarudzbenica(narudzbenica);
            this.stanjeLijekaRepository.save(tempStanje);
        }
        this.narudzbenicaRepository.save(narudzbenica);

    }
    @Transactional
    public List<NarudzbenicaDTO> dobaviSveNarudzbeniceAdmin(Long id) {
        List<NarudzbenicaDTO> narudzbenice =  new ArrayList<>();
        NarudzbenicaDTO temp;
        for(Narudzbenica narudzbenica : this.narudzbenicaRepository.findAll()){
            if(narudzbenica.getApoteka().getId().equals(id)){
                temp = new NarudzbenicaDTO(narudzbenica);
                temp.setBrojPonuda(narudzbenica.getPonude().size());
                narudzbenice.add(temp);
            }
        }
        return narudzbenice;
    }
}
