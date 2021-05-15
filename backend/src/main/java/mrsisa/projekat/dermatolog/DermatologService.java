package mrsisa.projekat.dermatolog;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
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
    @Autowired
    public DermatologService(DermatologRepository dermatologRepository,ApotekaRepository apotekaRepository){
        this.dermatologRepository = dermatologRepository;
        this.apotekeRepository = apotekaRepository;
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
        Apoteka apoteka = apotekeRepository.findById(id_apoteke).orElse(null);
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
