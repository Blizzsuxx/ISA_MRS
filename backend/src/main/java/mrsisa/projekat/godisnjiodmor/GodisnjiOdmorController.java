package mrsisa.projekat.godisnjiodmor;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.bezbjednost.UserTokenState;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/GodisnjiOdmori")
public class GodisnjiOdmorController {
    private final GodisnjiOdmorService  godisnjiOdmorService;

    @Autowired
    public GodisnjiOdmorController(GodisnjiOdmorService godisnjiOdmorService){
        this.godisnjiOdmorService = godisnjiOdmorService;
    }
    @GetMapping("/{id}")
    public GodisnjiOdmor dobaviGodisnjiOdmor(Long id){

        return godisnjiOdmorService.dobaviGodisnjiOdmor(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @GetMapping("/admin")
    public List<GodisnjiOdmorDTO> dobaviGodisnjeOdmoreAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        return godisnjiOdmorService.dobaviGodisnjeOdmoreAdmin(adminApoteke.getApoteka().getId());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/admin/odobriZahtjev/{id}")
    public ResponseEntity odobriZahtjev(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        boolean uredno = godisnjiOdmorService.odobriZahtjev(id);
        if(uredno)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/admin/odbijZahtjev/{id}")
    public ResponseEntity odbijZahtjev(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        boolean uredno = godisnjiOdmorService.odbijZahtjev(id);
        if(uredno)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



    @PostMapping("/zakaziGodisnji")
    public Boolean zakaziOdmor(@RequestBody Map<String, Object> data){
        return this.godisnjiOdmorService.zakaziOdmor(data);
        //napravi Odmor i dodaj u bazu
        //setuj odobren na null
        //params od, do, radnik (string : id)
    }

    @GetMapping ("/potvrdiGodisnji")
    public void potvrdiOdmor(@RequestBody Map<String, Object> data){
        // nadji odmor iz baze i setuj vrednost na true ili false
        // params : id, value
        try {
            MailSender.sendmail("promene oko odmora", "mahajiraaji@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
