package mrsisa.projekat.korisnik;

import mrsisa.projekat.radnik.Radnik;
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

    @Transactional
    public void izmeni(Map<String, Object> info, Radnik radnik) {
        radnik.setFirstName(info.get("ime").toString());
        radnik.setLastName(info.get("prezime").toString());
        radnik.setEmail(info.get("email").toString());
        radnik.setPassword(info.get("sifra").toString());
        radnik.setPromenioSifru(true);
        this.korisnikRepository.save(radnik);
    }
}
