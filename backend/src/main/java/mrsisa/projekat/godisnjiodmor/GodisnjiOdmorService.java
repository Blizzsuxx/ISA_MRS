package mrsisa.projekat.godisnjiodmor;


import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GodisnjiOdmorService {
    private final GodisnjiOdmorRepository godisnjiOdmorRepository;

    @Autowired
    public GodisnjiOdmorService(GodisnjiOdmorRepository godisnjiOdmorRepository){
        this.godisnjiOdmorRepository = godisnjiOdmorRepository;
    }

    public GodisnjiOdmor dobaviGodisnjiOdmor(Long id) {
        return null;
    }

    @Transactional
    public List<GodisnjiOdmorDTO> dobaviGodisnjeOdmoreAdmin(Long id) {
        List<GodisnjiOdmorDTO> godisnjiOdmori  =  new ArrayList<>();
        GodisnjiOdmorDTO temp;
        for(GodisnjiOdmor godisnjiOdmor: this.godisnjiOdmorRepository.findAll()){
            if(godisnjiOdmor.getApoteka().getId().equals(id)){
                temp = new GodisnjiOdmorDTO(godisnjiOdmor);
                temp.setKorisnicko(godisnjiOdmor.getRadnik().getUsername());
                godisnjiOdmori.add(temp);
            }
        }
        return godisnjiOdmori;
    }

    public boolean odobriZahtjev(Long id) {
        GodisnjiOdmor godisnjiOdmor =  this.godisnjiOdmorRepository.findById(id).orElse(null);
        if(godisnjiOdmor!=null) {
            godisnjiOdmor.setOdobren(true);
            this.godisnjiOdmorRepository.save(godisnjiOdmor);
            try {
                MailSender.sendmail("Vas zahtjev za godisnji odmor je odobren.", "andrijavojnovicpa@gmail.com");
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean odbijZahtjev(Long id) {
        GodisnjiOdmor godisnjiOdmor =  this.godisnjiOdmorRepository.findById(id).orElse(null);
        if(godisnjiOdmor!=null){
            godisnjiOdmor.setOdobren(false);
            this.godisnjiOdmorRepository.save(godisnjiOdmor);
            try {
                MailSender.sendmail("Vas zahtjev za godisnji odmor je odbijen.", "andrijavojnovicpa@gmail.com");
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

return false;
    }

    @Transactional
    public Boolean zakaziOdmor(Map<String, Object> data) {

        try {
            ArrayList<String> dateTime = (ArrayList<String>) data.get("datetime");
            LocalDateTime pocetak = LocalDateTime.parse(dateTime.get(0).substring(0, 23));
            LocalDateTime kraj = LocalDateTime.parse(dateTime.get(1).substring(0, 23));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Radnik radnik = (Radnik) auth.getPrincipal();
            GodisnjiOdmor godisnjiOdmor = new GodisnjiOdmor();
            godisnjiOdmor.setDatumOd(pocetak);
            godisnjiOdmor.setDatumDo(kraj);
            radnik.getGodisnjiOdmori().add(godisnjiOdmor);
            godisnjiOdmor.setRadnik(radnik);
        } catch (Exception e){
            return  false;
        }
        return true;
    }
}
