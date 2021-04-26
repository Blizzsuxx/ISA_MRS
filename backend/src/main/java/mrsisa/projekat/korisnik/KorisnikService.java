package mrsisa.projekat.korisnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
