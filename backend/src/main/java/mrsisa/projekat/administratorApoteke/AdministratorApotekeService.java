package mrsisa.projekat.administratorApoteke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
