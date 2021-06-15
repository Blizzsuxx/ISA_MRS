package mrsisa.projekat.zalba;

import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OdgovorService {

    private final OdgovorRepository odgovorRepository;
    private final ZalbaRepository zalbaRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    public OdgovorService(OdgovorRepository odgovorRepository, ZalbaRepository zalbaRepository){
        this.odgovorRepository = odgovorRepository;
        this.zalbaRepository = zalbaRepository;
    }

    public void save(Odgovor o){
        this.odgovorRepository.save(o);
    }

    public List<Odgovor> findAllByZalba(int id) {
        Zalba z = this.zalbaRepository.findById(id)
                .orElse(null);
        return this.odgovorRepository.findAllByZalba(z);
    }

    @Transactional
    public boolean sacuvaj(OdgovorDTO o) throws IOException, MessagingException {
        Zalba z = this.zalbaRepository.findById(o.getId()).orElse(null);
        if (z == null)
            return false;
        Odgovor odgovor = new Odgovor(o);
        odgovor.setZalba(z);
        if (z.getOdgovori().size() >= 1)
            return false;
        z.getOdgovori().add(odgovor);
        this.odgovorRepository.save(odgovor);
        this.zalbaRepository.save(z);
        MailSender.sendmail("Odgovor: " + o.getText(), "dunjica.isa@gmail.com");
        return true;
    }

    @Transactional
    public List<ZalbaDTO> vratiZalbeAdministratora(String korisnickoIme){
        List<ZalbaDTO> zalbe = new ArrayList<>();
        for (Odgovor o : this.odgovorRepository.findAll())
            if (o.getAdministrator().equals(korisnickoIme))
                zalbe.add(new ZalbaDTO(o.getZalba()));
        return zalbe;
    }
}
