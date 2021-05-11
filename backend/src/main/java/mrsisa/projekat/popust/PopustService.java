package mrsisa.projekat.popust;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopustService {
    private final PopustRepository popustRepository;

    @Autowired
    public PopustService(PopustRepository popustRepository){
        this.popustRepository = popustRepository;
    }

    public Popust findById(int id){
        return  this.popustRepository.findById(id);
    }

    public Popust save(Popust p){
        return this.popustRepository.save(p);
    }

}
