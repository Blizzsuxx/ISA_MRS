package mrsisa.projekat.dobavljac;

import mrsisa.projekat.dermatolog.Dermatolog;
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
@RequestMapping(path="api/v1/dobavljac")
public class DobavljacController {
    private final DobavljacService dobavljacService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public DobavljacController(DobavljacService dobavljacService,
                               ConfirmationTokenRepository confirmationTokenRepository){
        this.dobavljacService = dobavljacService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajDobavljaca")
    public void sacuvajDobavljaca(@RequestBody KorisnikDTO dummy) throws IOException, MessagingException {
        dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
        Dobavljac d = new Dobavljac(dummy);
        this.dobavljacService.save(d);

        ConfirmationToken confirmationToken = new ConfirmationToken(d);
        confirmationTokenRepository.save(confirmationToken);
        MailSender.sendmail("Da bi ste potvrdili prijavu, klknite da predloženi link : " +
                        "http://localhost:8080/api/korisnici/potvrda-registracije?token="+confirmationToken.getConfirmationToken(),
                "dunjica.isa@gmail.com");
    }

    @GetMapping(path = "/sviDobavljaci")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<KorisnikDTO> vratiSveDobavljace(){
        List<Dobavljac> dobavljaci = this.dobavljacService.findAll();
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for (Dobavljac d : dobavljaci)
            korisnici.add(new KorisnikDTO((Korisnik) d));

        return korisnici;
    }
}
