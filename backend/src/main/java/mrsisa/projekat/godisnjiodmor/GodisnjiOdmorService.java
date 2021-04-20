package mrsisa.projekat.godisnjiodmor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
