package mrsisa.projekat.narudzbenica;


import com.fasterxml.jackson.databind.ObjectMapper;
import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.ponuda.PonudaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NarudzbenicaService {

    private final NarudzbenicaRepository narudzbenicaRepository;
    private final ApotekaRepository apotekaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final LijekRepository lijekRepository;

    @Autowired
    public NarudzbenicaService(NarudzbenicaRepository narudzbenicaRepository, ApotekaRepository apotekaRepository,
                               StanjeLijekaRepository stanjeLijekaRepository, LijekRepository lijekRepository) {
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.apotekaRepository = apotekaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.lijekRepository = lijekRepository;
    }

    @Transactional
    public List<NarudzbenicaDTO> dobaviSveNarudzbenice(){
        List<NarudzbenicaDTO> narudzbenice = new ArrayList<>();
        for (Narudzbenica n : this.narudzbenicaRepository.findAll())
            narudzbenice.add(new NarudzbenicaDTO(n, true));
        return narudzbenice;
    }

    @Transactional
    public void kreirajNarudzbenicu(@RequestBody Map<String, Object> podaci, AdministratorApoteke administrator) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ObjectMapper mapper = new ObjectMapper();
        LocalDateTime datum = LocalDateTime.parse((String) podaci.get("datum"), format);
        Apoteka apoteka = this.apotekaRepository.findById(administrator.getApoteka().getId()).orElse(null);
        Narudzbenica narudzbenica = new Narudzbenica();
        narudzbenica.setApoteka(apoteka);
        narudzbenica.setRok(datum);
        narudzbenica.setZavrsena(false);
        narudzbenica.setPrihvacena(false);
        narudzbenica.setAdministratorApoteke(administrator);
        LijekDTO temp;
        StanjeLijeka tempStanje;
        Lijek tempLijek;
        for (Object object : (ArrayList<LijekDTO>) podaci.get("lijekovi")
        ) {
            tempStanje = new StanjeLijeka();
            temp = mapper.convertValue(object, LijekDTO.class);
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
        List<NarudzbenicaDTO> narudzbenice = new ArrayList<>();
        NarudzbenicaDTO temp;
        for (Narudzbenica narudzbenica : this.narudzbenicaRepository.findAll()) {
            if (narudzbenica.getApoteka().getId().equals(id)) {
                if(narudzbenica.getRok().isBefore(LocalDateTime.now())){
                    narudzbenica.setZavrsena(true);
                    this.narudzbenicaRepository.save(narudzbenica);
                }
                temp = new NarudzbenicaDTO(narudzbenica);
                temp.setBrojPonuda(narudzbenica.getPonude().size());
                for(StanjeLijeka stanjeLijeka: narudzbenica.getLijekovi()){
                    temp.dodajStanje(new StanjeLijekaDTO(stanjeLijeka));
                }
                narudzbenice.add(temp);
            }
        }
        return narudzbenice;
    }

    @Transactional
    public NarudzbenicaDTO dobaviJednuNarudzbeniceAdmin(Long narudzbenica_id,AdministratorApoteke adminApoteke) {
        Narudzbenica narudzbenica = this.narudzbenicaRepository.findById(narudzbenica_id).orElse(null);
        if (narudzbenica != null) {
            if(narudzbenica.getRok().isBefore(LocalDateTime.now())){
                narudzbenica.setZavrsena(true);
                this.narudzbenicaRepository.save(narudzbenica);
            }
            NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(narudzbenica);
            narudzbenicaDTO.setBrojPonuda(narudzbenica.getPonude().size());
            if(narudzbenica.getAdministratorApoteke().getId().equals(adminApoteke.getId())){
                narudzbenicaDTO.setPripada(true);
            }
            else{
                narudzbenicaDTO.setPripada(false);
            }
            for(StanjeLijeka stanjeLijeka: narudzbenica.getLijekovi()){
                narudzbenicaDTO.dodajStanje(new StanjeLijekaDTO(stanjeLijeka));
            }
            return narudzbenicaDTO;
        } else {
            return new NarudzbenicaDTO();
        }

    }

    @Transactional
    public void orbisiNarudzbenicu(Long id) {
        Narudzbenica narudzbenica = this.narudzbenicaRepository.findById(id).orElse(null);
        if(narudzbenica==null)
            return ;
        if (narudzbenica.getPonude()==null || narudzbenica.getPonude().size() == 0) {
            this.narudzbenicaRepository.delete(narudzbenica);
        }
    }
}
