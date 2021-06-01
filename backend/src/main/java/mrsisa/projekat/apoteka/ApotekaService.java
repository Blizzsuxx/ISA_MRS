package mrsisa.projekat.apoteka;


import com.sun.net.httpserver.HttpServer;
import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.adresa.AdresaRepository;
import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.akcija.AkcijaDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ApotekaService {
    private final ApotekaRepository apotekaRepository;
    private final AdresaRepository adresaRepository;
    private final LijekRepository lekRepository;
    private final PacijentRepository pacijentRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final RezervacijaRepository rezervacijaRepository;

    @Autowired
    public ApotekaService(ApotekaRepository apotekaRepository,AdresaRepository adresaRepository, LijekRepository l, PacijentRepository p,
                          StanjeLijekaRepository stanjeLijekaRepository,  RezervacijaRepository rezervacijaRepository){
        this.apotekaRepository = apotekaRepository;
        this.adresaRepository = adresaRepository;
        this.lekRepository=l;
        this.pacijentRepository=p;
        this.stanjeLijekaRepository=stanjeLijekaRepository;
        this.rezervacijaRepository=rezervacijaRepository;
    }

    public Apoteka save(Apoteka a){
        return this.apotekaRepository.save(a);
    }
    public List<Apoteka> findAll(){
        return this.apotekaRepository.findAll();
    }

    @Transactional
    public List<StanjeLijekaDTO> dobaviStanjaLijekova(Long id) {
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        if(apoteka== null)
            return new ArrayList<StanjeLijekaDTO>();
        List<StanjeLijekaDTO> povratna_stanja  =  new ArrayList<StanjeLijekaDTO>();
        for (StanjeLijeka sl:apoteka.getLijekovi())
        {
            povratna_stanja.add(new StanjeLijekaDTO(sl));
        }
        return povratna_stanja;
    }

    @Transactional
    public List<ApotekaDTO> dobaviApoteke(){
        List<ApotekaDTO> apoteke = new ArrayList<>();
        ApotekaDTO temp ;
    	for(Apoteka apoteka: this.apotekaRepository.findAll()){
    	    temp =  new ApotekaDTO(apoteka.getId(),apoteka.getIme());
    	    temp.setAdresa(apoteka.getAdresa());
    	    apoteke.add(temp);
        }
    	return apoteke;
    }

    @Transactional
    public List<ApotekaDTO> dobaviApotekePacijenta(Pacijent p){
        Pacijent pacijent= this.pacijentRepository.findByUsername(p.getUsername());
        List<ApotekaDTO> apoteke = new ArrayList<>();
        for (Apoteka apoteka: this.apotekaRepository.findAll()){
            apoteke.add(new ApotekaDTO(apoteka, apoteka.getPretplaceniPacijenti().contains(pacijent)));
        }
        return apoteke;
    }

    @Transactional
    public ApotekaDTO dobaviApoteku(Long id) {
        ApotekaDTO dto =  new ApotekaDTO(apotekaRepository.findById(id).orElse(null));
        
        return dto;
    }
    @Transactional
    public List<LijekDTO> dobaviLijekoveAlergija(Long id) { //TODO dobiti id osobe za oduzimanje lekova
        List<Lijek> sviLekovi= this.lekRepository.findAll();
        Pacijent pacijent= this.pacijentRepository.findOneById(9);
        List<LijekDTO> bezAlergija =new ArrayList<>();int a=0;
        for(Lijek l : sviLekovi) {
         a=0;
            for(Lijek lp : pacijent.getAlergije()){
                if(lp.getId()==l.getId()){
                    a=1;
                    break;
                }
            }if(a==0){
            bezAlergija.add(new LijekDTO(l));}
        }
        return bezAlergija;
    }
    @Transactional
    public ApotekaDTO dobaviApotekuAdmin(AdministratorApoteke adminApoteke) {
        Apoteka apoteka =  adminApoteke.getApoteka();
        apoteka = apotekaRepository.findById(apoteka.getId()).orElse(null);

        if(apoteka==null)
            return new ApotekaDTO();
        else{
            ApotekaDTO apotekaDTO =  new ApotekaDTO();
            apotekaDTO.setIme(apoteka.getIme());
            apotekaDTO.setId(apoteka.getId());
            apotekaDTO.setAdresa(apoteka.getAdresa());
            return apotekaDTO;
        }

    }
    @Transactional
    public void azurirajApotekuAdmin(AdministratorApoteke adminApoteke, ApotekaDTO apotekaDTO) {
        Apoteka apoteka =  adminApoteke.getApoteka();
        apoteka = apotekaRepository.findById(apoteka.getId()).orElse(null);
        System.out.println(apoteka.getIme());
        apoteka.setIme(apotekaDTO.getIme());
        System.out.println(apoteka.getIme());
        apoteka.postaviAdresuIzDTO(apotekaDTO);
        adresaRepository.save(apoteka.getAdresa());
        apotekaRepository.save(apoteka);
    }

    public List<StanjeLijekaDTO> dobaviSveDostupneLijekove() {
        //ovo iz svih apoteka vraca sve lekove, tj dto
        List<StanjeLijekaDTO> zaSlanje=new ArrayList<>();
        List<StanjeLijeka> stanja=this.stanjeLijekaRepository.findAll();
        for(StanjeLijeka s: stanja){
            if(s.getApoteka()!=null && s.getRezervacija()==null && s.geteRecept()==null && s.getNarudzbenica()==null ){
                zaSlanje.add(new StanjeLijekaDTO(s));
            }
        }

        return zaSlanje;

    }
    @Transactional
    public boolean rezervisiLek(String lek) {
        //todo  provera poena, akcija, pretplata,
        //Wed+Jun+16+2021+00%3A00%3A00+GMT+0200+%28Central+European+Summer+Time%29+1+undefined+3= ide id datum kolicina
        HashMap<String, Integer> meseci=new HashMap<>();
        meseci.put("Jun",6);meseci.put("Jul",7);meseci.put("Aug",8);meseci.put("Sep",9);meseci.put("Oct",10);
        meseci.put("Nov",11);meseci.put("Dec",12);meseci.put("Jan",1);meseci.put("Feb",2);meseci.put("Mart",3); meseci.put("Apr",4);meseci.put("may",5);
        //pazi salje se id stanja a ispisuje leka, to promeni ili ne?
        String podeljenTekst[]=lek.split("\\+");
        int mesec=meseci.get(podeljenTekst[1].trim()); //proveri da li i vreme treba da se zakaze
        LocalDateTime dan=LocalDateTime.of(Integer.parseInt(podeljenTekst[3].trim()),mesec, Integer.parseInt(podeljenTekst[2].trim()),0,0,0);
        String id=lek.split("\\+")[11];
        String kolicina1=lek.split("\\+")[13];
        Integer kolicina2=Integer.parseInt(kolicina1.split("=")[0].trim());
        Long poslatId=Long.parseLong(id.trim());
        List<StanjeLijeka> stanja=this.stanjeLijekaRepository.findAll();
        for(StanjeLijeka s: stanja){
            if(s.getApoteka()!=null && s.getRezervacija()==null && s.geteRecept()==null && s.getNarudzbenica()==null
                    && s.getId()==poslatId){
                Pacijent p=this.pacijentRepository.findOneById(9); //todo 9
                boolean alergija=proveriAlergije(p.getAlergije(), s.getLijek().getId());

                if(alergija){

                    return false;}

                Apoteka a=this.apotekaRepository.findOneById(1); //todo id apoteke? proveri
                ArrayList<StanjeLijeka> stanja2=new ArrayList<>();
                Rezervacija rez=new Rezervacija(130L, p,a,stanja2, dan ); //promeni id
                double cena=kolicina2*s.getCijena();
                StanjeLijeka novo=new StanjeLijeka(s,kolicina2, rez,cena);
                s.setKolicina(s.getKolicina()-kolicina2);
                this.stanjeLijekaRepository.save(s);
                p.getRezervacije().add(rez);
                //this.pacijentRepository.save(p);
               // this.stanjeLijekaRepository.save(novo);
                //this.rezervacijaRepository.save(rez);
                return true;
            }
        }return false;
    }

    private boolean proveriAlergije(List<Lijek> alergije, Long id) {
        for(Lijek l: alergije){
            if(l.getId()==id){return true;}
        }
        return false;
    }

    public void sacuvajApoteku(ApotekaDTO dummy) {
        Apoteka apoteka = new Apoteka(dummy);
        Adresa adresa =  new Adresa(dummy.getMjesto(),dummy.getPtt(), dummy.getUlica(), dummy.getBroj(),dummy.getDuzina(), dummy.getSirina());
        //this.adresaRepository.save(adresa);
        apoteka.setAdresa(adresa);
        this.apotekaRepository.save(apoteka);


    }
    @Transactional
    public List<StanjeLijekaDTO> dobaviStanjaLijekovaAdmin(Long id) {
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        if(apoteka== null)
            return new ArrayList<StanjeLijekaDTO>();
        List<StanjeLijekaDTO> povratna_stanja  =  new ArrayList<StanjeLijekaDTO>();
        StanjeLijekaDTO temp;
        for (StanjeLijeka sl:apoteka.getLijekovi())
        {
            temp = new StanjeLijekaDTO(sl);
            if(sl.getAkcija()!=null)
            temp.setAkcija(new AkcijaDTO(sl.getAkcija()));
            povratna_stanja.add(temp);

        }
        return povratna_stanja;
    }

    @Transactional
    public boolean pretplatiSeNaAApoteku(Pacijent pac, Long idApoteke){
        Apoteka a = this.apotekaRepository.findOneById(idApoteke);
        Pacijent p = this.pacijentRepository.findByUsername(pac.getUsername());

        for (Pacijent pacijent: a.getPretplaceniPacijenti())
            if (pacijent.getUsername().equals(p.getUsername()))
                return false;
        a.getPretplaceniPacijenti().add(p);

        this.apotekaRepository.save(a);

        return true;
    }
}
