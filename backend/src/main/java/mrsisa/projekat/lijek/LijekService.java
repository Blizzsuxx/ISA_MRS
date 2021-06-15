package mrsisa.projekat.lijek;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LijekService {
    private final LijekRepository lijekRepository;
    private final ApotekaRepository apotekaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    @Autowired
    public LijekService(LijekRepository lijekRepository,
                        ApotekaRepository apotekaRepository, StanjeLijekaRepository stanjeLijekaRepository){
        this.lijekRepository = lijekRepository;
        this.apotekaRepository = apotekaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
    }

    public Lijek save(Lijek l){
        return this.lijekRepository.save(l);
    }

    @Transactional
    public void sacuvajLijek(LijekDTO dto){
        Lijek l = new Lijek(dto);
        for (LijekDTO tempic: dto.getZamenskiLekovi())
            l.getZamenskiLijekovi().add(this.lijekRepository.findOneById(tempic.getId()));
        this.lijekRepository.save(l);
    }
    public Lijek findByNaziv(String naziv){
        return this.lijekRepository.findByNaziv(naziv);
    }

    public List<Lijek> findAll(){
        return this.lijekRepository.findAll();
    }

    public Page<Lijek> findAll(Pageable page){
        return this.lijekRepository.findAll(page);
    }

    public void remove(String naziv){
        this.lijekRepository.deleteByNaziv(naziv);
    }

    @Transactional
    public List<LijekDTO> dobaviLijekoveDobavljaca(Dobavljac d){
        List<LijekDTO> lijekoviDto = new ArrayList<>();
        for (Lijek l : this.lijekRepository.findAllByDobavljac(d)){
            lijekoviDto.add(new LijekDTO(l));
        }
        return lijekoviDto;
    }

    public List<Lijek> dobaviSveLijekove(){
        return List.of(new Lijek(
                1L,
                "andija",
                "andija",
                "andija",
                "andija",
                "andija",
                "andija"
        ));

    }
    @Transactional
    public List<LijekDTO> dobaviSveDTOLijekove(){
        List<Lijek> lijekovi = this.findAll();
        List<LijekDTO> lijekoviDTO = new ArrayList<>();

        for (Lijek l : lijekovi)
            lijekoviDTO.add(new LijekDTO(l));

        return lijekoviDTO;
    }

    @Transactional
    public List<LijekApotekaDTO> dobaviSveApotekaLijekDTO(long id){
        List<LijekApotekaDTO> lijekApoteka = new ArrayList<>();
        for (Apoteka a : this.apotekaRepository.findAll())
            for (StanjeLijeka sl : a.getLijekovi())
                if (sl.getLijek().getId() == id)
                    lijekApoteka.add(new LijekApotekaDTO(a.getIme(), sl.getCijena()));
        return lijekApoteka;
    }
}
