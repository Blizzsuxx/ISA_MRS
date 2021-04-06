package mrsisa.projekat.dermatolog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologService {


    private final DermatologRepository dermatologRepository;

    @Autowired
    public DermatologService(DermatologRepository dermatologRepository){
        this.dermatologRepository = dermatologRepository;
    }

    public List<Dermatolog> dobaviDermatologe(){


        return new ArrayList<Dermatolog>();
    }

}
