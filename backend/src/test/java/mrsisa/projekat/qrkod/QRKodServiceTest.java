package mrsisa.projekat.qrkod;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.PacijentRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QRKodServiceTest {
    @Mock
    private ApotekaRepository apotekaRepositoryMock;

    @Mock
    private EreceptRepository ereceptRepositoryMock;

    @Mock
    private StanjeLijekaRepository stanjeLijekaRepositoryMock;

    @Mock
    private PacijentRepository pacijentRepositoryMock;

    @InjectMocks
    private QRKodService qrKodService;

    @Test
    public void testVratiApotekeNaOsnovuKoda(){
        String[] tokeni1 = {"sifra1", "pacijent", "1:2,3:4"};

        Erecept e = new Erecept();
        e.setSifra("sifra1");

        when(ereceptRepositoryMock.findOneBySifra(e.getSifra())).thenReturn(Optional.of(e).orElse(null));
        assertNull(qrKodService.vratiApotekeNaOsnovuKoda(tokeni1));

        String[] tokeni2 = {"sifra2", "pacijent", "1:2,3:4"};

        Lijek l1 = new Lijek();
        l1.setId(1L);
        Lijek l2 = new Lijek();
        l2.setId(3L);

        StanjeLijeka sl1 = new StanjeLijeka();
        sl1.setId(1L);
        sl1.setLijek(l1);
        sl1.setKolicina(1000);
        StanjeLijeka sl2 = new StanjeLijeka();
        sl2.setId(2L);
        sl2.setLijek(l2);
        sl2.setKolicina(1000);
        Apoteka a1 = new Apoteka();
        a1.setLijekovi(new ArrayList<>());
        a1.getLijekovi().add(sl1);
        a1.getLijekovi().add(sl2);
        a1.setOcene(new ArrayList<>());


        StanjeLijeka sl3 = new StanjeLijeka();
        sl3.setId(3L);
        sl3.setLijek(l1);
        sl3.setKolicina(1000);
        StanjeLijeka sl4 = new StanjeLijeka();
        sl4.setId(4L);
        sl4.setLijek(l2);
        sl4.setKolicina(1000);
        Apoteka a2 = new Apoteka();
        a2.setLijekovi(new ArrayList<>());
        a2.getLijekovi().add(sl3);
        a2.getLijekovi().add(sl4);
        a2.setOcene(new ArrayList<>());

        List<Apoteka> apoteke = new ArrayList<>();
        apoteke.add(a1);
        apoteke.add(a2);
        when(apotekaRepositoryMock.findAll()).thenReturn(apoteke);
        assertEquals(2, qrKodService.vratiApotekeNaOsnovuKoda(tokeni2).size());

    }
}
