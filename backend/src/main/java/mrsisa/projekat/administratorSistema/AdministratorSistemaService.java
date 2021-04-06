package mrsisa.projekat.administratorSistema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AdministratorSistema> findAll(){
        return administratorSistemaRepository.findAll();
    }

    public Page<AdministratorSistema> findAll(Pageable page){
        return administratorSistemaRepository.findAll(page);
    }
}
