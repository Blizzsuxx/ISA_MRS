package mrsisa.projekat.ponuda;


import mrsisa.projekat.narudzbenica.NarudzbenicaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PonudaService {
    private final PonudaRepository ponudaRepository;


    @Autowired
    public PonudaService(PonudaRepository ponudaRepository){
        this.ponudaRepository = ponudaRepository;
    }
    @Transactional
    public List<PonudaDTO> dobaviSvePonudeNarudzbeniceAdmin(Long id) {
        List<PonudaDTO> ponude =  new ArrayList<>();
        for(Ponuda ponuda: this.ponudaRepository.findAll()){
            if(ponuda.getNarudzbenica().getId().equals(id)){
                ponude.add(new PonudaDTO(ponuda));
            }
        }
        return ponude;
    }

    public void prihvatiPonudu(Long id) {
        Ponuda ponuda = this.ponudaRepository.findById(id).orElse(null);
        if(ponuda!=null){
            ponuda.setStatus(2);
            this.ponudaRepository.save(ponuda);
        }

    }

    public void odbijPonudu(Long id) {
        Ponuda ponuda = this.ponudaRepository.findById(id).orElse(null);
        if(ponuda!=null){
            ponuda.setStatus(1);
            this.ponudaRepository.save(ponuda);
        }
    }
}
