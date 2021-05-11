package mrsisa.projekat.godisnjiodmor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GodisnjiOdmorService {
    private final GodisnjiOdmorRepository godisnjiOdmorRepository;

    @Autowired
    public GodisnjiOdmorService(GodisnjiOdmorRepository godisnjiOdmorRepository){
        this.godisnjiOdmorRepository = godisnjiOdmorRepository;
    }

    public GodisnjiOdmor dobaviGodisnjiOdmor(Long id) {
        return null;
    }

    @Transactional
    public List<GodisnjiOdmorDTO> dobaviGodisnjeOdmoreAdmin(Long id) {
        List<GodisnjiOdmorDTO> godisnjiOdmori  =  new ArrayList<>();
        GodisnjiOdmorDTO temp;
        for(GodisnjiOdmor godisnjiOdmor: this.godisnjiOdmorRepository.findAll()){
            if(godisnjiOdmor.getApoteka().getId().equals(id)){
                temp = new GodisnjiOdmorDTO(godisnjiOdmor);
                temp.setKorisnicko(godisnjiOdmor.getRadnik().getUsername());
                godisnjiOdmori.add(temp);
            }
        }
        return godisnjiOdmori;
    }
}
