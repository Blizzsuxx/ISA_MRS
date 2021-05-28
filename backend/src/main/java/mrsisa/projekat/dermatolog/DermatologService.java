package mrsisa.projekat.dermatolog;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmorRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.slobodanTermin.SlobodanTerminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologService {
    private final DermatologRepository dermatologRepository;
    private final ApotekaRepository apotekeRepository;
    private final PosetaRepository posetaRepository;
    private final SlobodanTerminRepository slobodanTerminRepository;
    private final GodisnjiOdmorRepository godisnjiOdmorRepository;
    @Autowired
    public DermatologService(DermatologRepository dermatologRepository, ApotekaRepository apotekaRepository, PosetaRepository posetaRepository,
                             SlobodanTerminRepository slobodanTerminRepository, GodisnjiOdmorRepository godisnjiOdmorRepository){
        this.dermatologRepository = dermatologRepository;
        this.apotekeRepository = apotekaRepository;
        this.posetaRepository = posetaRepository;
        this.slobodanTerminRepository = slobodanTerminRepository;
        this.godisnjiOdmorRepository =  godisnjiOdmorRepository;
    }
    @Transactional
    public List<DermatologDTO> dobaviDermatologe(){

        List<DermatologDTO> dermatolozi = new ArrayList<>();
        for(Dermatolog dermatolog:dermatologRepository.findAll()){
            dermatolozi.add(new DermatologDTO(dermatolog));
        }
        return dermatolozi;
    }

    public Dermatolog save(Dermatolog d){
        return this.dermatologRepository.save(d);
    }

    public List<Dermatolog> findAll(){
        return dermatologRepository.findAll();
    }

    public Page<Dermatolog> findAll(Pageable page){
        return dermatologRepository.findAll(page);
    }
    @Transactional
    public List<DermatologDTO> dobaviDermatologeAdmin(Long id) {
        Apoteka apoteka = apotekeRepository.findById(id).orElse(null);
        List<DermatologDTO> dermatolozi =  new ArrayList<>();
        DermatologDTO temp;
        List<String> apoteke;
        for(Dermatolog dermatolog: apoteka.getDermatolozi()){
            apoteke = new ArrayList<>();
            temp =  new DermatologDTO(dermatolog);
            for(Apoteka apoteka1 : dermatolog.getApoteke()){
                apoteke.add(apoteka1.getIme());
            }
            temp.setApoteke(apoteke);
            dermatolozi.add(temp);
        }
        return dermatolozi;
    }
    @Transactional
    public void otpustiDermatologa(Integer id,Long id_apoteke) {
        //izbrisati i povez
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
        for(Dermatolog dermatolog: apoteka.getDermatolozi()){
            if(dermatolog.getId().equals(id)){
                apoteka.getDermatolozi().remove(dermatolog);
                apotekeRepository.save(apoteka);
                return;
            }
        }
    }

    @Transactional
    public List<DermatologDTO> dobaviNezaposleneDermatologeAdmin(Long id) {
        Apoteka apoteka =  this.apotekeRepository.findById(id).orElse(null);
        List<DermatologDTO> dermatolozi =  new ArrayList<>();
        List<Dermatolog> zaposleni = apoteka.getDermatolozi();
        DermatologDTO temp;
        List<String> apoteke;
        for(Dermatolog dermatolog: this.dermatologRepository.findAll()){
            if(!zaposleni.contains(dermatolog)){
                apoteke = new ArrayList<>();
                temp =  new DermatologDTO(dermatolog);
                for(Apoteka apoteka1 : dermatolog.getApoteke()){
                    apoteke.add(apoteka1.getIme());
                }
                temp.setApoteke(apoteke);
                dermatolozi.add(temp);
            }
        }
        return dermatolozi;
    }
    @Transactional
    public void zaposliDermatologa(Integer id,Long id_apoteke) {
        Apoteka apoteka = apotekeRepository.findById(id_apoteke).orElse(null);
        Dermatolog dermatolog =  this.dermatologRepository.findById(id).orElse(null);
        if (dermatolog!=null){
            apoteka.getDermatolozi().add(dermatolog);
        }
    }
}
