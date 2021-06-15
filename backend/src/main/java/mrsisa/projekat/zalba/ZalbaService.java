package mrsisa.projekat.zalba;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.farmaceut.FarmaceutRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.korisnik.KorisnikRepository;
import mrsisa.projekat.korisnik.KorisnikService;
import mrsisa.projekat.pacijent.TempDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ZalbaService {

    private ZalbaRepository zalbaRepository;
    private ApotekaRepository apotekaRepository;
    private KorisnikRepository korisnikRepository;

    @Autowired
    public ZalbaService(ZalbaRepository zalbaRepository, ApotekaRepository apotekaRepository,
                        KorisnikRepository korisnikRepository){
        this.zalbaRepository = zalbaRepository;
        this.apotekaRepository = apotekaRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public void save(Zalba z){
        this.zalbaRepository.save(z);
    }

    @Transactional
    public List<Zalba> findAllByPacijent(String pacijent){
        return this.zalbaRepository.findAllByPacijent(pacijent);
    }

    public List<Zalba> findAll(){
        return this.zalbaRepository.findAll();
    }

    @Transactional
    public ApotekaDTO vratiInfoApoteka(TempDTO tempDTO){
        return new ApotekaDTO(this.apotekaRepository.findOneById(Long.parseLong(tempDTO.getIdentifikator())));
    }

    @Transactional
    public KorisnikDTO vratiInfoKorisnika(TempDTO tempDTO){
        return new KorisnikDTO(this.korisnikRepository.findById(Integer.parseInt(tempDTO.getIdentifikator())).orElseThrow());
    }
}
