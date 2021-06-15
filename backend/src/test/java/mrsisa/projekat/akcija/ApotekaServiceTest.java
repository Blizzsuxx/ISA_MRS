package mrsisa.projekat.akcija;


import mrsisa.projekat.administratorApoteke.AdministratorApotekeRepository;
import mrsisa.projekat.adresa.AdresaRepository;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.apoteka.ApotekaService;
import mrsisa.projekat.apoteka.IzvjestajDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.popust.PopustRepository;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApotekaServiceTest {
    @Mock
    private ApotekaRepository apotekaRepository;
    @Mock
    private  AdresaRepository adresaRepository;
    @Mock
    private  LijekRepository lekRepository;
    @Mock
    private  PacijentRepository pacijentRepository;
    @Mock
    private StanjeLijekaRepository stanjeLijekaRepository;
    @Mock
    private  RezervacijaRepository rezervacijaRepository;
    @Mock
    private  PosetaRepository posetaRepository;
    @Mock
    private  AkcijaRepository akcijaRepository;
    @Mock
    private  PopustRepository popustRepository;
    @Mock
    private AdministratorApotekeRepository administratorApotekeRepository;

    @InjectMocks
    private ApotekaService apotekaService;


    @Test
    @Transactional
    public void TestIzvjestaj(){
        Lijek lijek  = new Lijek("siw23","Paracetamol","tableta","kruzni","lijep","nema");
        StanjeLijeka stanjeLijeka =  new StanjeLijeka(lijek,10,true);
        stanjeLijeka.setCijena(20);
        Akcija akcija = new Akcija(10, LocalDateTime.now().plusDays(10),LocalDateTime.now().plusDays(20));
        stanjeLijeka.setAkcija(akcija);
        Apoteka apoteka = new Apoteka(1L,"Prva",new ArrayList<>());
        apoteka.setOcene(new ArrayList<>());
        apoteka.setDermatolozi(new ArrayList<>());
        apoteka.setFarmaceuti(new ArrayList<>());

        ArrayList<StanjeLijeka> stanja1 =  new ArrayList<>();
        stanja1.add(stanjeLijeka);
        Rezervacija rezervacija =  new Rezervacija(1L,null,apoteka,stanja1,LocalDateTime.now());
        List<Rezervacija> rezervacije  = new ArrayList<>();
        rezervacije.add(rezervacija);
        rezervacija.setIzdato(true);
        apoteka.setRezervacije(new ArrayList<>());
        apoteka.getLijekovi().add(stanjeLijeka);
        apoteka.getRezervacije().add(rezervacija);
        List<Apoteka> apoteke = new ArrayList<>();
        when(apotekaRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(apoteka));
        when(rezervacijaRepository.findAll()).thenReturn(rezervacije);
        IzvjestajDTO izvjestaj =  apotekaService.izvjestaj(1L);

        System.out.println("Andrija konj");
        for(String kljuc : izvjestaj.getLijekoviGodine().keySet()){
            System.out.println(kljuc);
            System.out.println(izvjestaj.getLijekoviGodine().get(kljuc));
        }
        int vrijednosti  = izvjestaj.getLijekoviGodine().get("2021");
        assertEquals(vrijednosti, 10);

    }
}
