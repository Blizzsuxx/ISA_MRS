package mrsisa.projekat.korisnik;

import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.radnik.RadnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class KorisnikService implements UserDetailsService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik k = korisnikRepository.findByUsername(username);
        if (k == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else{
            return k;
        }

    }

    public Korisnik findByUsername(String username){
        return korisnikRepository.findByUsername(username);
    }

    public Korisnik save(Korisnik k){
        return korisnikRepository.save(k);
    }

    public void remove(String korisnickoIme) {
        korisnikRepository.deleteByUsername(korisnickoIme);
    }

    public Korisnik findByEmailIdIgnoreCase(String email) {
        return korisnikRepository.findByEmailIgnoreCase(email);
    }
    
    @Transactional
    public void izmeni(Map<String, Object> info, RadnikDTO radnikDTO) {

        Radnik radnik = (Radnik) this.korisnikRepository.findById(radnikDTO.getId()).orElse(null);
        if(!(info.get("ime") == null) && !info.get("ime").toString().isEmpty()){
            radnik.setFirstName(info.get("ime").toString());
        }
        if(!(info.get("prezime") == null) && !info.get("prezime").toString().isEmpty()){
            radnik.setLastName(info.get("prezime").toString());
        }
        if(!(info.get("sifra") == null) && !info.get("sifra").toString().isEmpty()){
            radnik.setPassword(info.get("sifra").toString());
            radnik.setPromenioSifru(true);

        }

        this.korisnikRepository.save(radnik);
    }
}
