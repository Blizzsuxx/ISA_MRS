package mrsisa.projekat.slobodanTermin;


import org.springframework.stereotype.Service;

@Service
public class SlobodanTerminService {
    final private SlobodanTerminRepository slobodanTerminRepository;

    public SlobodanTerminService(SlobodanTerminRepository slobodanTerminRepository){
        this.slobodanTerminRepository = slobodanTerminRepository;
    }
}
