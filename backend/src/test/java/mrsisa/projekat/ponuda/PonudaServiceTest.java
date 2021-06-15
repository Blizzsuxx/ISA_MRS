package mrsisa.projekat.ponuda;

import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.dobavljac.DobavljacRepository;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.narudzbenica.NarudzbenicaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijekaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertFalse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        
    }

}
