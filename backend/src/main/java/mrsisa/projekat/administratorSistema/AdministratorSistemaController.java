package mrsisa.projekat.administratorSistema;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
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
@RequestMapping(path="api/v1/administratorSistema")
public class AdministratorSistemaController {
    private final AdministratorSistemaService administratorSistemaService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Autowired
    public AdministratorSistemaController(AdministratorSistemaService administratorSistemaService
    , ConfirmationTokenRepository confirmationTokenRepository) {
        this.administratorSistemaService = administratorSistemaService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajAdministratoraSistema")
    public void sacuvajAdministratoraSistema(@RequestBody KorisnikDTO dummy) throws IOException, MessagingException {
        dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
        AdministratorSistema as = new AdministratorSistema(dummy);
        this.administratorSistemaService.save(as);

        ConfirmationToken confirmationToken = new ConfirmationToken(as);
        confirmationTokenRepository.save(confirmationToken);
        MailSender.sendmail("Da bi ste potvrdili prijavu, klknite da predloženi link : " +
                        "http://localhost:8080/api/korisnici/potvrda-registracije?token="+confirmationToken.getConfirmationToken(),
                "dunjica.isa@gmail.com");
    }

    @GetMapping(path = "/sviAdministratoriSistema")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveAdministratoreSistema(){
        List<AdministratorSistema> admini = this.administratorSistemaService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (AdministratorSistema as : admini)
            korisnici.add(new KorisnikDTO((Korisnik) as));

        return korisnici;
    }
}
