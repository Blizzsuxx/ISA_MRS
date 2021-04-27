package mrsisa.projekat.pacijent;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.administratorApoteke.AdministratorApotekeRepository;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PacijentConfig {
  /*  @Bean
    CommandLineRunner commandLineRunnerPacijent(PacijentRepository repozitorijum, LijekRepository lijekR,  ApotekaRepository apotekaR, StanjeLijekaRepository stanjeLijekaR){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Pacijent p=new Pacijent("pera", "pera2", "pera","pera", LocalDateTime.now()); p.setId(7);
       List<Lijek> lek=new ArrayList<>();
        //Adresa a=new Adresa("mesto", "ptt", "ulica",  "45", 50,50);
        //p.setAdresa(a);
        System.out.println(p.getFirstName() +" "+ " "+p.getId()+" "+ " "+p.getLastName()+" "+ " "+p.getEmail()+" "+ " "+p.getBirthday());

        p.setEmail("pera@gmail.com");
         lek.add(lijekR.findById(4L).orElse(null));
        lek.add(lijekR.findById(5L).orElse(null));
        lek.add(lijekR.findById(6L).orElse(null));

        p.setAlergije(lek);

        ArrayList<Rezervacija> rez=new ArrayList<>();
        Apoteka ap=apotekaR.findOneById(2L);

        ArrayList<StanjeLijeka> rezLek=new ArrayList<>();
        ArrayList<StanjeLijeka> rezLek2=new ArrayList<>();

        stanjeLijekaR.save(new StanjeLijeka(
                        10L,
                        lijekR.findById(1L).orElse(null),
                        10,
                        false,
                        10.00,
                        LocalDateTime.parse("01-04-2021 10:10",format),
                        apotekaR.findById(1L).orElse(null)
                ));
        rezLek.add(stanjeLijekaR.findById(10L).orElse(null));
        System.out.println(rezLek.isEmpty());
       /* rezLek.add(stanjeLijekaR.findById(2L).orElse(null));
        rezLek.get(1).setCijena(500);

        rezLek2.add(stanjeLijekaR.findById(1L).orElse(null));
        rezLek2.get(0).setCijena(1000);
        rezLek2.add(stanjeLijekaR.findById(3L).orElse(null));
        rezLek2.get(1).setCijena(500);

        Rezervacija r= new Rezervacija(1L,p,ap,rezLek, LocalDateTime.now());
        Rezervacija r2= new Rezervacija(2L,p,ap,rezLek2, LocalDateTime.now());
        rez.add(r);
        rez.add(r2);

        p.setRezervacije(rez);
        StanjeLijeka s1=stanjeLijekaR.findById(1L).orElse(null);
        s1.setKolicina(1);
        StanjeLijeka s2=stanjeLijekaR.findById(2L).orElse(null);
        s2.setKolicina(2);
        List<StanjeLijeka> sl=new ArrayList<>();
        sl.add(s1);
        sl.add(s2);

        Erecept rec=new Erecept();
        rec.setPacijent(p);
        rec.setDatumIzdavanja(LocalDateTime.now());
        rec.setSifra("1");
        rec.setPrepisaniLijekovi(sl);

        Erecept rec1=new Erecept();
        rec1.setPacijent(p);
        rec1.setDatumIzdavanja(LocalDateTime.now());
        rec1.setSifra("1");
        rec1.setPrepisaniLijekovi(sl);

        List<Erecept> li=new ArrayList<>();
        li.add(rec);
        li.add(rec1);
        p.setIzdatiPrekoERecepta(li);
        p.seteRecepti(li);
        System.out.println(p.getFirstName() +" "+ " "+p.getId()+" "+ " "+p.getLastName()+" "+ " "+p.getEmail()+" "+ " "+p.getBirthday());
        return args ->{
            repozitorijum.save(p);

        };
    }*/
}
