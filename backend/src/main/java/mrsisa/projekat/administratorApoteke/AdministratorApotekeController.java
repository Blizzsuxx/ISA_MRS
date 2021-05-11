package mrsisa.projekat.administratorApoteke;

import mrsisa.projekat.korisnik.ConfirmationToken;
import mrsisa.projekat.korisnik.ConfirmationTokenRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/administratorApoteke")
public class AdministratorApotekeController {
    private final AdministratorApotekeService administratorApotekeService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdministratorApotekeController(AdministratorApotekeService administratorApotekeService,
                                          ConfirmationTokenRepository confirmationTokenRepository) {
        this.administratorApotekeService = administratorApotekeService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajAdministratoraApoteke")
    public void sacuvajAdministratoraApoteke(@RequestBody KorisnikDTO dummy) throws IOException, MessagingException {
        dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
        AdministratorApoteke aa = new AdministratorApoteke(dummy);
        this.administratorApotekeService.save(aa);
        ConfirmationToken confirmationToken = new ConfirmationToken(aa);
        confirmationTokenRepository.save(confirmationToken);

        MailSender.sendmail("To confirm your account, please click here : " +
                            "http://localhost:8080/api/korisnici/potvrda-registracije?token="+confirmationToken.getConfirmationToken(),
                "dunjica.isa@gmail.com");
    }

    @GetMapping(path = "/sviAdministratoriApoteke")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveAdministratoreApoteka(){
        List<AdministratorApoteke> admini = this.administratorApotekeService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (AdministratorApoteke ap : admini)
            korisnici.add(new KorisnikDTO((Korisnik) ap));

        return korisnici;
    }
}
