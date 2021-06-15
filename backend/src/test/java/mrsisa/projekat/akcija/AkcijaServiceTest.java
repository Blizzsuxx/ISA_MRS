package mrsisa.projekat.akcija;

import static org.junit.Assert.assertEquals;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import mrsisa.projekat.zalba.ZalbaRepository;
import mrsisa.projekat.zalba.ZalbaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AkcijaServiceTest {

    @Mock
    private AkcijaRepository akcijaRepository;

    @Mock
    private StanjeLijekaRepository stanjeLijekaRepository;

    @Mock
    private ApotekaRepository apotekaRepository;

    @InjectMocks
    private AkcijaService akcijaService;


    @Test
    @Transactional
    public void TestVratiAkcije(){
        Lijek lijek  = new Lijek("siw23","Paracetamol","tableta","kruzni","lijep","nema");
        StanjeLijeka stanjeLijeka =  new StanjeLijeka(lijek,10,true);
        stanjeLijeka.setCijena(10);
        Akcija akcija = new Akcija(10, LocalDateTime.now().plusDays(10),LocalDateTime.now().plusDays(20));
        stanjeLijeka.setAkcija(akcija);
        Apoteka apoteka = new Apoteka(1L,"Prva",new ArrayList<>());
        apoteka.getLijekovi().add(stanjeLijeka);
        List<Apoteka> apoteke = new ArrayList<>();

        apoteke.add(apoteka);
        when(apotekaRepository.findOneById(1L)).thenReturn(apoteka);
        List<AkcijaDTO> akcije =  akcijaService.vratiAkcije("1");
        assertEquals(akcije.size(), 0);




    }

    @Test
    @Transactional
    public void TestVratiAkcije2(){
        Lijek lijek  = new Lijek("Paracetamol","Paracetamol","tableta","kruzni","lijep","nema");
        lijek.setId(1L);

        StanjeLijeka stanjeLijeka =  new StanjeLijeka(lijek,10,true);
        stanjeLijeka.setDatumIstekaCijene(LocalDateTime.now());
        stanjeLijeka.setCijena(10);
        Akcija akcija = new Akcija(10, LocalDateTime.now().minusDays(10),LocalDateTime.now().plusDays(20));
        stanjeLijeka.setAkcija(akcija);
        Apoteka apoteka = new Apoteka(1L,"Prva",new ArrayList<>());
        apoteka.getLijekovi().add(stanjeLijeka);
        List<Apoteka> apoteke = new ArrayList<>();
        apoteke.add(apoteka);
        when(apotekaRepository.findOneById(1L)).thenReturn(apoteka);
        List<AkcijaDTO> akcije =  akcijaService.vratiAkcije("1");
        assertEquals(akcije.size(), 1);
    }
}
