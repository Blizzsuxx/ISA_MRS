package mrsisa.projekat.godisnjiodmor;


import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("--------");
            System.out.println(godisnjiOdmor.getApoteka().getId());
            System.out.println(id);
            System.out.println("--------");
            if(godisnjiOdmor.getApoteka().getId().equals(id)){
                temp = new GodisnjiOdmorDTO(godisnjiOdmor);
                temp.setKorisnicko(godisnjiOdmor.getRadnik().getUsername());
                godisnjiOdmori.add(temp);
            }
        }
        return godisnjiOdmori;
    }

    public void odobriZahtjev(Long id) {
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
        }
    }

    public void odbijZahtjev(Long id) {
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
        }


    }
}
