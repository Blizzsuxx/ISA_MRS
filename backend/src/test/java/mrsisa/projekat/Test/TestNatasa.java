package mrsisa.projekat.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.pacijent.PacijentService;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNatasa {

    @InjectMocks
    private PacijentService pacijentService;

    @Mock
    private RezervacijaRepository rezervacijaRepositoryMock;
    @Mock
    private PacijentRepository pacijentRepository;

    @Mock
    private DermatologRepository dermatologRepositoryMock;

    @Test
    public void testOceni(){

        Dermatolog dermatolog1=new Dermatolog("dermatolog1", "dermatolog1", "dermatolog1", "dermatolog1", "dermatolog1", LocalDateTime.now());
        Pacijent pacijent1=new Pacijent();
        pacijent1.setId(1);
        dermatolog1.setId(2);
        dermatolog1.setOcene(new ArrayList<>());
        pacijent1.setOcene(new ArrayList<>());
        when(dermatologRepositoryMock.findByIdD(2)).thenReturn(dermatolog1);
        when(pacijentRepository.findOneById(1)).thenReturn(pacijent1);
        String ocena="D2a5"; //format koji saljem
        pacijentService.posaljiOcenu(ocena);
        assertEquals(dermatolog1.getOcene().get(0).getOcena(), 5);


    }
    @Test
    public void testIzmeniPodatke(){
        Pacijent pacijent1=new Pacijent();
        pacijent1.setId(1);
        pacijent1.setEmail("sima@gmail.com");
        List<String> podaci=new ArrayList<>();
        podaci.add("novo");
        podaci.add("ime");
        podaci.add("mesto");
        podaci.add("ulica");
        podaci.add("broj");
        podaci.add("sima@gmail.com");
        podaci.add("2001-2-3T1");
        pacijent1.setAdresa(new Adresa());
        when(pacijentRepository.findByEmail("sima@gmail.com")).thenReturn(pacijent1);

        pacijentService.promeni(podaci);
        assertEquals(pacijent1.getLastName(), "ime");
        assertEquals(pacijent1.getEmail(),"sima@gmail.com");
    }
    @Test
    public void testDobaviRezerervacije(){
        Pacijent pacijent1=new Pacijent();
        pacijent1.setId(1);
        pacijent1.setRezervacije(new ArrayList<>());
        Rezervacija r1=new Rezervacija();
        r1.setOdustao(false);

        Apoteka apo=new Apoteka();apo.setId(1L);
        r1.setApoteka(apo);
        r1.setDatumRezervacije(LocalDateTime.of(2021,7, 25,0,0,0));
        r1.setIzdato(false);
        StanjeLijeka st1=new StanjeLijeka();
        Lijek l1=new Lijek(1L, "String naziv", "String vrstaLijeka", "String oblikLijeka", "String sastav", "tring proizvodjac"," String napomena");
        st1.setKolicina(4);
        st1.setLijek(l1);
        st1.setProdaja(true);
        st1.setId(1L);
        st1.setCijena(1000);

        StanjeLijeka st2=new StanjeLijeka();
        Lijek l2=new Lijek(2L, "String naziv2", "String vrstaLijeka2", "String oblikLijeka", "String sastav", "tring proizvodjac"," String napomena");
        st2.setKolicina(4);
        st2.setLijek(l2);
        st2.setProdaja(false);
        st2.setId(2L);
        st2.setCijena(1000);
        r1.setRezervisaniLijekovi(new ArrayList<>());
        r1.getRezervisaniLijekovi().add(st1);

        Rezervacija r2=new Rezervacija();
        r2.setRezervisaniLijekovi(new ArrayList<StanjeLijeka>());
        r2.setOdustao(false);
        r2.setApoteka(apo);
        r2.setDatumRezervacije(LocalDateTime.of(2021,7, 25,0,0,0));
        r2.setIzdato(false);
        pacijent1.getRezervacije().add(r1);
        pacijent1.getRezervacije().add(r2);
        List<Rezervacija> rezs=new ArrayList<>();
        rezs.add(r1);
        rezs.add(r2);
        when(rezervacijaRepositoryMock.findAllByUsername("n")).thenReturn((rezs));
        assertEquals(pacijentService.dobaviRezervacije().size(), 1);

    }

}
