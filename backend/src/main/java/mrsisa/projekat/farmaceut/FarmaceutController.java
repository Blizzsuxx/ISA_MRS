package mrsisa.projekat.farmaceut;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.dermatolog.DermatologDTO;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.korisnik.ConfirmationToken;
import mrsisa.projekat.korisnik.ConfirmationTokenRepository;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/farmaceut")
public class  FarmaceutController {
    private final FarmaceutService farmaceutService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public FarmaceutController(FarmaceutService farmaceutService,
                               ConfirmationTokenRepository confirmationTokenRepository){
        this.farmaceutService = farmaceutService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;


    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin")
    public List<DermatologDTO> dobaviFarmaceuteAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return farmaceutService.dobaviFarmaceuteAdmin(adminApoteke.getApoteka().getId());
    }


    @PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ROLE_ADMIN_APOTEKA')")
    @GetMapping(path="/admin/nezaposleni")
    public List<DermatologDTO> dobaviNezaposleneFarmaceuteAdmin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        return farmaceutService.dobaviNezaposleneFarmaceuteAdmin(adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/otpustiFarmaceuta/{id}")
    public void otpustiFarmaceuta(@PathVariable Integer id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();

        farmaceutService.otpustiDermatologa(id,adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping(path="/zaposliFarmaceuta/{id}")
    public void zaposliFarmaceuta(@PathVariable Integer id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        farmaceutService.zaposliFarmaceuta(id,adminApoteke.getApoteka().getId());
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajFarmaceuta")
    public void sacuvajFarmaceuta(@RequestBody KorisnikDTO dummy) throws IOException, MessagingException {
        dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
        Farmaceut f = new Farmaceut(dummy);
        this.farmaceutService.save(f);

        ConfirmationToken confirmationToken = new ConfirmationToken(f);
        confirmationTokenRepository.save(confirmationToken);
        MailSender.sendmail("Da bi ste potvrdili prijavu, klknite da predloženi link : " +
                        "http://localhost:8080/api/korisnici/potvrda-registracije?token="+confirmationToken.getConfirmationToken(),
                "dunjica.isa@gmail.com");
    }

}
