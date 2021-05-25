package mrsisa.projekat.ponuda;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.narudzbenica.NarudzbenicaDTO;
import mrsisa.projekat.narudzbenica.NarudzbenicaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PonudaService {
    private final PonudaRepository ponudaRepository;
    private final NarudzbenicaRepository narudzbenicaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;

    @Autowired
    public PonudaService(PonudaRepository ponudaRepository, NarudzbenicaRepository narudzbenicaRepository,
                         StanjeLijekaRepository stanjeLijekaRepository) {
        this.ponudaRepository = ponudaRepository;
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
    }

    @Transactional
    public List<PonudaDTO> dobaviSvePonudeDostavljaca(Dobavljac d){
        List<PonudaDTO> ponude = new ArrayList<>();
        for (Ponuda ponuda : this.ponudaRepository.findAllByDobavljac(d)){
            ponude.add(new PonudaDTO(ponuda));
        }
        return ponude;
    }
    @Transactional
    public List<PonudaDTO> dobaviSvePonudeNarudzbeniceAdmin(Long id) {
        List<PonudaDTO> ponude = new ArrayList<>();
        for (Ponuda ponuda : this.ponudaRepository.findAll()) {
            if (ponuda.getNarudzbenica().getId().equals(id)) {
                ponude.add(new PonudaDTO(ponuda));
            }
        }
        return ponude;
    }

    @Transactional
    public void prihvatiPonudu(Long id, Long narudzbenica_id, AdministratorApoteke administratorApoteke) {
        boolean postoji = false;
        StanjeLijeka temp;
        Ponuda ponuda = this.ponudaRepository.findById(id).orElse(null);
        if (ponuda != null) {
            Narudzbenica narudzbenica = this.narudzbenicaRepository.findById(narudzbenica_id).orElse(null);
            if (narudzbenica != null) {
                if(!narudzbenica.getAdministratorApoteke().getId().equals(administratorApoteke.getId())){
                    return;
                }
                ponuda.setStatus(2);
                this.ponudaRepository.save(ponuda);
                narudzbenica.setPrihvacena(true);
                this.narudzbenicaRepository.save(narudzbenica);
                Apoteka apoteka = narudzbenica.getApoteka();
                for (StanjeLijeka stanjeLijeka : narudzbenica.getLijekovi()) {
                    postoji = false;
                    for (StanjeLijeka stanjeLijeka1 : apoteka.getLijekovi()) {
                        if (stanjeLijeka.getLijek().getId().equals(stanjeLijeka1.getLijek().getId())) {
                            postoji = true;
                            stanjeLijeka1.setKolicina(stanjeLijeka1.getKolicina() + stanjeLijeka.getKolicina());
                            this.stanjeLijekaRepository.save(stanjeLijeka1);
                        }
                    }
                    if (!postoji) {
                        temp = new StanjeLijeka(stanjeLijeka.getLijek(), stanjeLijeka.getKolicina(), false);
                        temp.setApoteka(apoteka);
                        this.stanjeLijekaRepository.save(temp);
                    }

                }
            }

            for(Ponuda ponudaTemp: this.ponudaRepository.findAll()){
                if(ponudaTemp.getNarudzbenica().getId().equals(narudzbenica_id) && ponudaTemp.getId().equals(id)){
//                    try {
//                        System.out.println("Mejl je poslat");
//                        MailSender.sendmail("Vasa ponuda je prihvacena.", ponudaTemp.getDobavljac().getEmail());
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
                if(ponudaTemp.getNarudzbenica().getId().equals(narudzbenica_id) && !ponudaTemp.getId().equals(id)){
                    ponudaTemp.setStatus(1);
                    this.ponudaRepository.save(ponudaTemp);
//                    try {
//                        System.out.println("Mejl je poslat");
//                        MailSender.sendmail("Vasa ponuda je odbijena.", ponudaTemp.getDobavljac().getEmail());
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }

    }

}
