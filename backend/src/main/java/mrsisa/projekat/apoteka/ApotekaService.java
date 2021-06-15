package mrsisa.projekat.apoteka;


import com.sun.net.httpserver.HttpServer;
import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.adresa.AdresaRepository;
import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.akcija.AkcijaDTO;
import mrsisa.projekat.akcija.AkcijaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.popust.Popust;
import mrsisa.projekat.popust.PopustRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import javax.servlet.http.HttpServletRequest;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ApotekaService {
    private final ApotekaRepository apotekaRepository;
    private final AdresaRepository adresaRepository;
    private final LijekRepository lekRepository;
    private final PacijentRepository pacijentRepository;
    private final StanjeLijekaRepository stanjeLijekaRepository;
    private final RezervacijaRepository rezervacijaRepository;
    private final PosetaRepository posetaRepository;
    private final AkcijaRepository akcijaRepository;
    private final PopustRepository popustRepository;

    @Autowired
    public ApotekaService(ApotekaRepository apotekaRepository,AdresaRepository adresaRepository, LijekRepository l, PacijentRepository p,AkcijaRepository akcijaRepository,
                          PopustRepository popustRepository,StanjeLijekaRepository stanjeLijekaRepository,  RezervacijaRepository rezervacijaRepository,PosetaRepository posetaRepository){
        this.apotekaRepository = apotekaRepository;
        this.adresaRepository = adresaRepository;
        this.lekRepository=l;
        this.pacijentRepository=p;
        this.stanjeLijekaRepository=stanjeLijekaRepository;
        this.rezervacijaRepository=rezervacijaRepository;
        this.posetaRepository =  posetaRepository;
        this.akcijaRepository=akcijaRepository;
        this.popustRepository=popustRepository;
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
        StanjeLijekaDTO temp;
        for (StanjeLijeka sl:apoteka.getLijekovi())
        {
            if(!sl.getLijek().isSamoRecept()){
            temp =  new StanjeLijekaDTO(sl);
            temp.setImeApoteke(sl.getApoteka().getIme());
            if(sl.getAkcija()!=null){
                temp.setAkcija(new AkcijaDTO(sl.getAkcija()));
            }
            povratna_stanja.add(temp);}
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


    public Korisnik getTrenutnogKorisnika(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik k = (Korisnik)auth.getPrincipal();
        return k;
    }


    @Transactional
    public List<LijekDTO> dobaviLijekoveAlergija(Long id) {
        List<Lijek> sviLekovi= this.lekRepository.findAll();
        Korisnik k=getTrenutnogKorisnika();
        Pacijent pacijent= this.pacijentRepository.findOneById(k.getId());
        //Pacijent pacijent= this.pacijentRepository.findOneByUsername(id);
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
    @Transactional
    public List<StanjeLijekaDTO> dobaviSveDostupneLijekove() {
        //ovo iz svih apoteka vraca sve lekove, tj dto
        List<StanjeLijekaDTO> zaSlanje=new ArrayList<>();
        List<StanjeLijeka> stanja=this.stanjeLijekaRepository.findAll();
        StanjeLijekaDTO temp;
        for(StanjeLijeka s: stanja){
            if(s.getApoteka()!=null && s.getRezervacija()==null && s.geteRecept()==null && s.getNarudzbenica()==null ){
                temp = new StanjeLijekaDTO(s);
                temp.setImeApoteke(s.getApoteka().getIme());
                zaSlanje.add(temp);
            }
        }

        return zaSlanje;

    }
    @Transactional//(propagation  = Propagation.REQUIRES_NEW)//(propagation = Propagation.REQUIRES_NEW)
    public String rezervisiLek(String lek) {
        //todo  provera poena, akcija, pretplata,
        //Wed+Jun+16+2021+00%3A00%3A00+GMT+0200+%28Central+European+Summer+Time%29+1+undefined+3= ide id datum kolicina
        HashMap<String, Integer> meseci=new HashMap<>();
        meseci.put("Jun",6);meseci.put("Jul",7);meseci.put("Aug",8);meseci.put("Sep",9);meseci.put("Oct",10);
        meseci.put("Nov",11);meseci.put("Dec",12);meseci.put("Jan",1);meseci.put("Feb",2);meseci.put("Mart",3); meseci.put("Apr",4);meseci.put("May",5);
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
                if(s.getKolicina()-kolicina2>=0){
                    Korisnik k=getTrenutnogKorisnika();
                    Pacijent p=this.pacijentRepository.findOneById(k.getId());
                    boolean alergija=proveriAlergije(p.getAlergije(), s.getLijek().getId());

                    if(alergija){

                        return "false";}

                    Apoteka a=s.getApoteka();
                    ArrayList<StanjeLijeka> stanja2=new ArrayList<>();
                    List<Rezervacija> sveRez=this.rezervacijaRepository.findAll();
                    long novId=(long)(sveRez.size()+1);
                    Rezervacija rez=new Rezervacija(novId, p,a,stanja2, dan ); //promeni id
                    double cena=kolicina2*s.getCijena();
                    StanjeLijeka novo=new StanjeLijeka(s,kolicina2, rez,cena);
                    s.setKolicina(s.getKolicina()-kolicina2);
                    this.stanjeLijekaRepository.save(s);
                    p.getRezervacije().add(rez);
                    //this.pacijentRepository.save(p);
                   // this.stanjeLijekaRepository.save(novo);
                    if(s.getAkcija()!=null){
                        if((s.getAkcija().getDatumOd().isBefore(LocalDateTime.now()) || s.getAkcija().getDatumOd().isEqual(LocalDateTime.now()))
                                &&(s.getAkcija().getDatumDo().isAfter(LocalDateTime.now()) || s.getAkcija().getDatumDo().isAfter(LocalDateTime.now()))){
                            novo.setCijena(novo.getCijena()*(1-(s.getAkcija().getProcenatPopusta()/100)));
                        }
                    }
                    System.out.println("ovde popust1");
                    //Apoteka apoteka=this.apotekaRepository.findOneById(r.getApoteka().getId()).orElse(null)
                    //List<Popust> popusti=//todo proveri da li samo 1 postojipopusti.get(0);
                    Popust popust=this.popustRepository.findById(1);//. orElse(null);
                    System.out.println("ovde popust2");
                    if(popust!=null){
                    if(p.getBrojPoena()<=popust.getDoRegular()){
                    novo.setCijena(novo.getCijena()*(1-(popust.getPopustRegular()/100)));}
                    else if(p.getBrojPoena()<=popust.getDoSilver() && p.getBrojPoena()>popust.getDoRegular()){
                        novo.setCijena(novo.getCijena()*(1-(popust.getPopustSilver()/100)));}
                    else {
                    novo.setCijena(novo.getCijena()*(1-(popust.getPopustGold()/100)));
                }

                    System.out.println(rez.getDatumRezervacije());
                    System.out.println("rezervise se");
                    this.rezervacijaRepository.save(rez); //todo sacuvaj kolicinu leka i proveri da li treba onako da ide save
                    return "true "+rez.getId();
                }}
            }

        }return "false";
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
    @Transactional
    public IzvjestajDTO izvjestaj(Long id) {
        IzvjestajDTO izvjestaj =  new IzvjestajDTO();
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        if(apoteka==null)
            return izvjestaj;
        double ocjena = 0;
        for(Ocena ocena: apoteka.getOcene())
            ocjena+=ocena.getOcena();
        if(apoteka.getOcene().size()==0)
            izvjestaj.setOcjenaApoteke(0.0);
        else
            izvjestaj.setOcjenaApoteke(ocjena/apoteka.getOcene().size());
        for(Dermatolog dermatolog:apoteka.getDermatolozi()){
            ocjena = 0;
            for(Ocena ocena:dermatolog.getOcene())
                ocjena+=ocena.getOcena();
            if(dermatolog.getOcene().size()==0)
                izvjestaj.getOcjeneDermatologa().put(dermatolog.getUsername(),0.0);
            else
                izvjestaj.getOcjeneDermatologa().put(dermatolog.getUsername(),ocjena/dermatolog.getOcene().size());
        }
        for(Farmaceut farmaceut:apoteka.getFarmaceuti()){
            ocjena = 0;
            for(Ocena ocena:farmaceut.getOcene())
                ocjena+=ocena.getOcena();
            if(farmaceut.getOcene().size()==0)
                izvjestaj.getOcjeneDermatologa().put(farmaceut.getUsername(),0.0);
            else
                izvjestaj.getOcjeneDermatologa().put(farmaceut.getUsername(),ocjena/farmaceut.getOcene().size());}
        int mjesec = 1;
        for(int i=0;i<12;i++){
            izvjestaj.getPreglediPoMjesecima().add(0);
        }
        for(Poseta poseta: this.posetaRepository.findAll()){
            if(poseta.getOtkazano()==null)
                continue;
            if(poseta.getApoteka().getId().equals(apoteka.getId()) && poseta.getPocetak().getYear()==LocalDateTime.now().getYear() && !poseta.getOtkazano()){
                izvjestaj.getPreglediPoMjesecima().set(poseta.getPocetak().getMonthValue() - 1, izvjestaj.getPreglediPoMjesecima().get(poseta.getPocetak().getMonthValue() - 1)+1);
            }
        }


        int pocetna_godina = LocalDateTime.now().getYear() -10;
        int suma;
        for(;pocetna_godina<=LocalDateTime.now().getYear();pocetna_godina++){
            suma =0;
            for(Poseta poseta: this.posetaRepository.findAll()){
                if(poseta.getOtkazano()==null)
                    continue;
                if(poseta.getApoteka().getId().equals(apoteka.getId()) && poseta.getPocetak().getYear()==pocetna_godina && !poseta.getOtkazano()){
                   suma++;
                }
            }
            izvjestaj.getPreglediGodine().put(String.valueOf(pocetna_godina),suma);
        }

        // izdati lijekovi



        for(int i=0;i<12;i++){
            izvjestaj.getLijekoviPoMjesecima().add(0);
        }
        for(Rezervacija rezervacija: apoteka.getRezervacije()){
            if(!rezervacija.isIzdato())
                continue;
            if(rezervacija.getDatumRezervacije().getYear()==LocalDateTime.now().getYear() && !rezervacija.isOdustao()){
                suma = 0;
                for(StanjeLijeka stanjeLijeka : rezervacija.getRezervisaniLijekovi()){
                    suma +=stanjeLijeka.getKolicina();
                }
                izvjestaj.getLijekoviPoMjesecima().set(rezervacija.getDatumRezervacije().getMonthValue() - 1, izvjestaj.getPreglediPoMjesecima().get(rezervacija.getDatumRezervacije().getMonthValue() - 1)+suma);
            }
        }




        pocetna_godina = LocalDateTime.now().getYear() -10;
        for(;pocetna_godina<=LocalDateTime.now().getYear();pocetna_godina++){
            suma = 0;
            for(Rezervacija rezervacija: apoteka.getRezervacije()){
                if(!rezervacija.isIzdato())
                    continue;
                if(rezervacija.getDatumRezervacije().getYear()==pocetna_godina && !rezervacija.isOdustao()){
                    for(StanjeLijeka stanjeLijeka : rezervacija.getRezervisaniLijekovi()) {
                        suma += stanjeLijeka.getKolicina();
                    }
                }
            }
            izvjestaj.getLijekoviGodine().put(String.valueOf(pocetna_godina),suma);
        }
        return izvjestaj;
    }

    @Transactional
    public IzvjestajDTO izvjestajPeriod(Long id, String datumOd, String datumDo) {
        IzvjestajDTO izvjestajDTO  = new IzvjestajDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime datumOdParsiran =  LocalDateTime.parse(datumOd,dtf);
        LocalDateTime datumDoParsiran = LocalDateTime.parse(datumDo,dtf);
        Apoteka apoteka = this.apotekaRepository.findById(id).orElse(null);
        int i= 0 ;
        int suma;
        while(!datumOdParsiran.plusDays(i).isAfter(datumDoParsiran)){
            suma = 0;
            for(Rezervacija rezervacija: apoteka.getRezervacije()){
                if(!rezervacija.isIzdato())
                    continue;
                if(rezervacija.getDatumRezervacije().getYear()==datumDoParsiran.getYear() && !rezervacija.isOdustao()
                    && rezervacija.isIzdato() && rezervacija.getDatumRezervacije().isAfter(datumOdParsiran.plusDays(i)) &&
                        rezervacija.getDatumRezervacije().isBefore(datumDoParsiran)){
                    for(StanjeLijeka stanjeLijeka : rezervacija.getRezervisaniLijekovi()) {
                        suma += stanjeLijeka.getCijena();
                    }
                }
            }
            izvjestajDTO.getPrihodiPeriod().put(dtf2.format(datumOdParsiran.plusDays(i)),suma);
            i++;
        }
        System.out.println("Sto ovo ne radi");
        return izvjestajDTO;



    }
}
