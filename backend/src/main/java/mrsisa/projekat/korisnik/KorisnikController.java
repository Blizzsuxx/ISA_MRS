package mrsisa.projekat.korisnik;

import mrsisa.projekat.bezbjednost.JwtAuthenticationRequest;
import mrsisa.projekat.bezbjednost.UserTokenState;
import mrsisa.projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path="api/korisnici")
@CrossOrigin
public class KorisnikController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        Korisnik user = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername(), user.getRole());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, (long) expiresIn, user.getRole()));
    }

    @GetMapping(produces = "application/json", value = "/dobaviKorisnika/{korisnickoIme}")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public KorisnikDTO getKorisnik(@PathVariable String korisnickoIme){
        Korisnik k = this.korisnikService.findByUsername(korisnickoIme);
        if (k == null){
            return null;
        }

        return new KorisnikDTO(k);
    }

    @PutMapping(consumes = "application/json", value="/azurirajKorisnika")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public String azurirajKorisnika(@RequestBody KorisnikDTO dummy){
        Korisnik k = this.korisnikService.findByUsername(dummy.getKorisnickoIme());

        if (k == null)
            return null;

        k.setFirstName(dummy.getIme());
        k.setLastName(dummy.getPrezime());
        k.setBirthday(LocalDateTime.parse(dummy.getRodjendan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        this.korisnikService.save(k);

        return k.getRole();
    }

    @Transactional
    @DeleteMapping(value="/obrisiKorisnika/{korisnickoIme}")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public String obrisiKorisnika(@PathVariable String korisnickoIme){
        Korisnik k = this.korisnikService.findByUsername(korisnickoIme);

        if (k != null){
            this.korisnikService.remove(korisnickoIme);
            return k.getRole();
        } else {
            return null;
        }
    }
}
