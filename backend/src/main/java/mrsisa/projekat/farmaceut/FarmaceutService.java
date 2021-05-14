package mrsisa.projekat.farmaceut;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmaceutService {
    private final FarmaceutRepository farmaceutRepository;
    private final ApotekaRepository apotekeRepository;
    public FarmaceutService(FarmaceutRepository farmaceutRepository,ApotekaRepository apotekaRepository){
        this.farmaceutRepository = farmaceutRepository;
        this.apotekeRepository = apotekaRepository;
    }

    @Transactional
    public List<DermatologDTO> dobaviFarmaceuteAdmin(Long id) {
        Apoteka apoteka = apotekeRepository.findById(id).orElse(null);
        List<DermatologDTO> farmaceuti =  new ArrayList<>();

        DermatologDTO temp ;
        List<String>  apoteke;
        for(Farmaceut farmaceut: apoteka.getFarmaceuti()){
            temp = new DermatologDTO(farmaceut);
            apoteke =  new ArrayList<>();
            apoteke.add(farmaceut.getApoteka().getIme());
            temp.setApoteke(apoteke);
            farmaceuti.add(temp);
        }
        return farmaceuti;
    }

    public void otpustiDermatologa(Integer id) {
        Farmaceut farmaceut =  this.farmaceutRepository.findById(id).orElse(null);
        if(farmaceut!=null){
            farmaceut.setApoteka(null);
            this.farmaceutRepository.save(farmaceut);
        }
    }

    @Transactional
    public List<DermatologDTO> dobaviNezaposleneFarmaceuteAdmin(Long id) {
        List<DermatologDTO> farmaceuti =  new ArrayList<>();
        for(Farmaceut farmaceut:this.farmaceutRepository.findAll()){
            if(farmaceut.getApoteka()==null){
                farmaceuti.add(new DermatologDTO(farmaceut));
            }
        }
        return farmaceuti;
    }
    @Transactional
    public void zaposliFarmaceuta(Integer id,Long apoteka_id) {
        Apoteka apoteka = this.apotekeRepository.findById(apoteka_id).orElse(null);
        Farmaceut farmaceut =  this.farmaceutRepository.findById(id).orElse(null);
        if(farmaceut !=null){
            farmaceut.setApoteka(apoteka);
            this.farmaceutRepository.save(farmaceut);
        }
    }
}
