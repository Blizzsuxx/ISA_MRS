package mrsisa.projekat.ponuda;

import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.dobavljac.DobavljacRepository;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.narudzbenica.NarudzbenicaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PonudaServiceTest {
    @Mock
    private PonudaRepository ponudaRepositoryMock;

    @Mock
    private NarudzbenicaRepository narudzbenicaRepositoryMock;

    @Mock
    private StanjeLijekaRepository stanjeLijekaRepositoryMock;

    @Mock
    private DobavljacRepository dobavljacRepositoryMock;

    @InjectMocks
    private PonudaService ponudaService;

    @Test
    public void testKreirajPonudu(){
        PonudaDTO dto = new PonudaDTO();
        dto.setId(1L);
        dto.setDobavljac("marko");
        dto.setNazivPonude("ponuda1");
        dto.setCijenaPonude(1000);
        dto.setStatus(0);
        dto.setRokPonude("2021-07-12");
        dto.setRokNarudzbenice("2021-07-13");

        Narudzbenica n1 = new Narudzbenica();
        n1.setId(1L);
        n1.setRok(LocalDateTime.parse("2021-06-14 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setIdNarudzbenice(1L);

        when(narudzbenicaRepositoryMock.findById(n1.getId())).thenReturn(Optional.of(n1));
        Dobavljac d = new Dobavljac();
        d.setUsername("marko");
        assertFalse(ponudaService.kreirajPonudu(dto, d));

        Narudzbenica n2 = new Narudzbenica();
        n2.setId(2L);
        n2.setRok(LocalDateTime.parse("2021-06-14 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setIdNarudzbenice(2L);
        when(narudzbenicaRepositoryMock.findById(n2.getId())).thenReturn(Optional.of(n2));
        assertFalse(ponudaService.kreirajPonudu(dto, d));

        Narudzbenica n3 = new Narudzbenica();
        n3.setId(3L);
        n3.setRok(LocalDateTime.parse("2021-07-13 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setIdNarudzbenice(3L);
        Lijek l1 = new Lijek();
        l1.setId(1L);
        Lijek l2 = new Lijek();
        l2.setId(2L);
        StanjeLijeka st1 = new StanjeLijeka();
        st1.setLijek(l1);
        StanjeLijeka st2 = new StanjeLijeka();
        st2.setLijek(l2);

        n3.setLijekovi(new ArrayList<>());
        n3.getLijekovi().add(st1);
        n3.getLijekovi().add(st2);
        d.setLijekoviNaStanju(new ArrayList<>());
        d.getLijekoviNaStanju().add(l1);
        d.getLijekoviNaStanju().add(l2);

        when(dobavljacRepositoryMock.findById(d.getId())).thenReturn(Optional.of(d));
        when(narudzbenicaRepositoryMock.findById(n3.getId())).thenReturn(Optional.of(n3));
        assertTrue(ponudaService.kreirajPonudu(dto, d));

    }

}
