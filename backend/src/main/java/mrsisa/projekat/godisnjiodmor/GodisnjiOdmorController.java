package mrsisa.projekat.godisnjiodmor;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
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
    public void odobriZahtjev(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        godisnjiOdmorService.odobriZahtjev(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN_APOTEKA')")
    @PutMapping("/admin/odbijZahtjev/{id}")
    public void odbijZahtjev(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdministratorApoteke adminApoteke = (AdministratorApoteke)auth.getPrincipal();
        godisnjiOdmorService.odbijZahtjev(id);
    }



    @PostMapping("/zakaziGodisnji")
    public Boolean zakaziOdmor(@RequestBody Map<String, Object> data){
        //napravi Odmor i dodaj u bazu
        //setuj odobren na null
        //params od, do, radnik (string : id)
        return true;
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
