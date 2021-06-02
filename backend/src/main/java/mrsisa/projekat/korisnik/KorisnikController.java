package mrsisa.projekat.korisnik;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.bezbjednost.JwtAuthenticationRequest;
import mrsisa.projekat.bezbjednost.UserTokenState;
import mrsisa.projekat.radnik.RadnikDTO;
import mrsisa.projekat.util.MailSender;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping(path="api/korisnici")
@CrossOrigin
public class KorisnikController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private MailSender mailSender;

    @GetMapping("/potvrda-registracije")
    public boolean potvrdaRegistracije(@RequestParam("token")String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null){
            Korisnik k = korisnikService.findByEmailIdIgnoreCase(token.getKorisnik().getEmail());
            k.setPotvrdaEmail(true);
            korisnikService.save(k);
        } else {
            return false;
        }
        return true;
    }
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
        String temp;
        if (!user.isPotvrdaEmail())
            temp = "NEMA";
        else
            temp = user.getRole();
        return ResponseEntity.ok(new UserTokenState(jwt, (long) expiresIn, temp));
    }

    @GetMapping(path = "trenutniRadnik")
    public RadnikDTO trenutniRadnik(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Radnik radnik = (Radnik)auth.getPrincipal();
        RadnikDTO radnikDTO = new RadnikDTO(radnik);
        System.out.println(radnikDTO.getIme());
        return radnikDTO;
    }

    @PostMapping(path = "/izmeni")
    public void izmeni(@RequestBody Map<String, Object> info){

        this.korisnikService.izmeni(info, trenutniRadnik());
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

    @GetMapping(value = "/promjenaLozinke/{lozinka}")
    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'DOBAVLJAC','ROLE_ADMIN_APOTEKA')")
    public boolean promjeniLozinku(@PathVariable String lozinka){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        if (k.getPassword().equals(passwordEncoder.encode(lozinka)))
            return false;

        k.setPassword(passwordEncoder.encode(lozinka));
        k.setPrijavljen(true);
        this.korisnikService.save(k);
        return true;
    }

    @GetMapping(value="/potvrdaPrijave")
    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'DOBAVLJAC','ROLE_ADMIN_APOTEKA')")
    public boolean potvrdaPrijave(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return k.isPrijavljen();
    }

    @GetMapping(produces = "application/json", value = "/dobaviTrenutnogKorisnika")
    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'DOBAVLJAC')")
    public KorisnikDTO getTrenutnogKorisnik(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return new KorisnikDTO(k);
    }

    @PutMapping(consumes = "application/json", value="/azurirajNalog")
    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA', 'DOBAVLJAC')")
    public KorisnikDTO azurirajNalog(@RequestBody KorisnikDTO dummy){
        Korisnik k = this.korisnikService.findByUsername(dummy.getKorisnickoIme());

        if (k == null)
            return null;

        k.setFirstName(dummy.getIme());
        k.setLastName(dummy.getPrezime());
        k.setBirthday(LocalDateTime.parse(dummy.getRodjendan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        if (!dummy.getSifra().isEmpty())
            k.setPassword(passwordEncoder.encode(dummy.getSifra()));

        this.korisnikService.save(k);

        return dummy;
    }
}
