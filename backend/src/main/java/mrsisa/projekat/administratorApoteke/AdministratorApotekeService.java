package mrsisa.projekat.administratorApoteke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class AdministratorApotekeService {
    private final AdministratorApotekeRepository administratorApotekeRepository;

    @Autowired
    public AdministratorApotekeService(AdministratorApotekeRepository administratorApotekeRepository) {
        this.administratorApotekeRepository = administratorApotekeRepository;
    }

    public AdministratorApoteke save(AdministratorApoteke a){
        return this.administratorApotekeRepository.save(a);
    }

    public List<AdministratorApoteke> findAll(){
        return administratorApotekeRepository.findAll();
    }

    public Page<AdministratorApoteke> findAll(Pageable page){
        return administratorApotekeRepository.findAll(page);
    }
}
