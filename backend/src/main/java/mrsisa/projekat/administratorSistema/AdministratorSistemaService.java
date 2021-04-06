package mrsisa.projekat.administratorSistema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorSistemaService {
    private final AdministratorSistemaRepository administratorSistemaRepository;

    @Autowired
    public AdministratorSistemaService(AdministratorSistemaRepository administratorSistemaRepository) {
        this.administratorSistemaRepository = administratorSistemaRepository;
    }

    public AdministratorSistema save(AdministratorSistema a){
        return this.administratorSistemaRepository.save(a);
    }
}
