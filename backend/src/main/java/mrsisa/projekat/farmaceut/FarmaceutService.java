package mrsisa.projekat.farmaceut;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologDTO;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmorRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
import mrsisa.projekat.radnoVrijeme.RadnoVrijemeRepository;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmaceutService {
    private final FarmaceutRepository farmaceutRepository;
    private final ApotekaRepository apotekeRepository;
    private final PosetaRepository posetaRepository;
    private final SlobodanTerminRepository slobodanTerminRepository;
    private final RadnoVrijemeRepository radnoVrijemeRepository;
    private final GodisnjiOdmorRepository godisnjiOdmorRepository;
    public FarmaceutService(FarmaceutRepository farmaceutRepository,ApotekaRepository apotekaRepository,
                            PosetaRepository posetaRepository,SlobodanTerminRepository slobodanTerminRepository,
                            GodisnjiOdmorRepository godisnjiOdmorRepository,RadnoVrijemeRepository radnoVrijemeRepository){
        this.farmaceutRepository = farmaceutRepository;
        this.apotekeRepository = apotekaRepository;
        this.posetaRepository = posetaRepository;
        this.slobodanTerminRepository = slobodanTerminRepository;
        this.godisnjiOdmorRepository = godisnjiOdmorRepository;
        this.radnoVrijemeRepository = radnoVrijemeRepository;
    }

    @Transactional
    public List<DermatologDTO> dobaviFarmaceuteAdmin(Long id) {
        Apoteka apoteka = apotekeRepository.findById(id).orElse(null);
        List<DermatologDTO> farmaceuti =  new ArrayList<>();
        if(apoteka==null)
            return farmaceuti;
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

    public void otpustiDermatologa(Integer id,Long id_apoteke) {
        Apoteka apoteka = apotekeRepository.findById(id_apoteke).orElse(null);
        for (Poseta poseta: this.posetaRepository.findAll()){
            if(poseta.getApoteka().getId().equals(id_apoteke) && poseta.getRadnik().getId().equals(id)
            ){
                poseta.setOtkazano(true);
                posetaRepository.save(poseta);
            }
        }
        for (SlobodanTermin slobodanTermin : this.slobodanTerminRepository.findAll()){
            if(slobodanTermin.getApoteka().getId().equals(id_apoteke) && slobodanTermin.getRadnik().getId().equals(id)){
                this.slobodanTerminRepository.delete(slobodanTermin);
            }
        }

        for (GodisnjiOdmor godisnjiOdmor : this.godisnjiOdmorRepository.findAll()){
            if(godisnjiOdmor.getApoteka().getId().equals(id_apoteke) && godisnjiOdmor.getRadnik().getId().equals(id)){
                this.godisnjiOdmorRepository.delete(godisnjiOdmor);
            }
        }
        for(RadnoVrijeme radnoVrijeme : this.radnoVrijemeRepository.findAll()){
            if(radnoVrijeme.getApoteka().getId().equals(id_apoteke) && radnoVrijeme.getFarmaceuet().getId().equals(id)){
                this.radnoVrijemeRepository.delete(radnoVrijeme);
            }
        }
        for(Farmaceut farmaceut: apoteka.getFarmaceuti()){
            if(farmaceut.getId().equals(id)){
                apoteka.getFarmaceuti().remove(farmaceut);
                apotekeRepository.save(apoteka);
                return;
            }
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
            RadnoVrijeme radnoVrijeme =  new RadnoVrijeme(apoteka,null,farmaceut, LocalDateTime.of(2000,10,10,7,0),LocalDateTime.of(2000,10,10,15,0));
            farmaceut.setApoteka(apoteka);
            this.radnoVrijemeRepository.save(radnoVrijeme);
            this.farmaceutRepository.save(farmaceut);
        }
    }
}
