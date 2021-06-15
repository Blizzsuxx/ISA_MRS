package mrsisa.projekat.ponuda;


import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.dobavljac.DobavljacRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.narudzbenica.NarudzbenicaDTO;
import mrsisa.projekat.narudzbenica.NarudzbenicaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import mrsisa.projekat.util.MailSender;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PonudaService {
    private final PonudaRepository ponudaRepository;
    private final NarudzbenicaRepository narudzbenicaRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final DobavljacRepository dobavljacRepository;

    @Autowired
    public PonudaService(PonudaRepository ponudaRepository, NarudzbenicaRepository narudzbenicaRepository,
                         StanjeLijekaRepository stanjeLijekaRepository, DobavljacRepository dobavljacRepository) {
        this.ponudaRepository = ponudaRepository;
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.stanjeLijekaRepository = stanjeLijekaRepository;
        this.dobavljacRepository = dobavljacRepository;
    }

    @Transactional
    public boolean kreirajPonudu(PonudaDTO ponudaDTO, Dobavljac d){
        Ponuda p = new Ponuda(ponudaDTO);
        Narudzbenica n = this.narudzbenicaRepository.findById(ponudaDTO.getIdNarudzbenice()).orElseThrow();
        if (LocalDateTime.now().isAfter(n.getRok()))
            return false;

        if(LocalDate.parse(p.getRokPonude(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(n.getRok().toLocalDate()))
            return false;

        for (Ponuda ponudice : this.ponudaRepository.findAllByDobavljac(d)){
            if (ponudice.getNarudzbenica().getId().equals(n.getId()))
                return false;
        }

        for (StanjeLijeka sl : n.getLijekovi()){
            boolean indikator = false;
            for (Lijek l : this.dobavljacRepository.findById(d.getId()).orElseThrow().getLijekoviNaStanju()){
                if (sl.getLijek().getId().equals(l.getId())){
                    indikator = true;
                    break;
                }
            }
            if (!indikator)
                return false;
        }

        p.setDobavljac(d);
        p.setNarudzbenica(n);
        this.ponudaRepository.save(p);
        return true;
    }

    @Transactional
    public NarudzbenicaDTO dobaviNarudzbenicuPonude(Long id){
        return new NarudzbenicaDTO(this.ponudaRepository.findById(id).orElseThrow().getNarudzbenica(), true);
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
    public void azurirajPonudu(PonudaDTO ponudaDTO){
        Ponuda p = this.ponudaRepository.findById(ponudaDTO.getId()).orElseThrow();
        p.setNazivPonude(ponudaDTO.getNazivPonude());
        p.setCijenaPonude(ponudaDTO.getCijenaPonude());
        this.ponudaRepository.save(p);
    }
    @Transactional
    public void prihvatiPonudu(Long id, Long narudzbenica_id, AdministratorApoteke administratorApoteke, ArrayList<StanjeLijekaDTO> stanjaLijekova) {
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
                        temp = new StanjeLijeka(stanjeLijeka.getLijek(), stanjeLijeka.getKolicina(), true);
                        temp.setApoteka(apoteka);
                        for(StanjeLijekaDTO noveCijene: stanjaLijekova){
                            if(temp.getLijek().getId().equals(noveCijene.getLijek().getId())){
                                temp.setCijena(noveCijene.getCijena());
                                temp.setDatumIstekaCijene(LocalDateTime.now());
                            }
                        }
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
    @Transactional
    public List<Long> pripremi(Long id, Long n_id, AdministratorApoteke adminApoteke) {
        List<Long> nepostojeciLijekovi = new ArrayList<Long>();
        Ponuda ponuda = this.ponudaRepository.findById(id).orElse(null);
        if(ponuda==null)
            return new ArrayList<Long>();
        Narudzbenica narudzbenica = this.narudzbenicaRepository.findById(n_id).orElse(null);
        if(narudzbenica==null)
            return new ArrayList<Long>();
        Apoteka apoteka = narudzbenica.getApoteka();
        boolean postoji;
        for(StanjeLijeka stanjeLijeka: narudzbenica.getLijekovi()){
            postoji  = false;
            for(StanjeLijeka ap_stanjeLijeka: apoteka.getLijekovi()){
                if(stanjeLijeka.getLijek().getId().equals(ap_stanjeLijeka.getLijek().getId())){
                    postoji =  true;
                }
            }
            if(!postoji)
                nepostojeciLijekovi.add(stanjeLijeka.getLijek().getId());
        }

        return nepostojeciLijekovi;
    }
}
