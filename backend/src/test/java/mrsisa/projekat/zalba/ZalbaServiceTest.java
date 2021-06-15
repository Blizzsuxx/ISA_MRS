package mrsisa.projekat.zalba;

import static org.junit.Assert.assertEquals;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.korisnik.KorisnikRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZalbaServiceTest {
    @Mock
    private ZalbaRepository zalbaRepositoryMock;

    @Mock
    private ApotekaRepository apotekaRepositoryMock;

    @Mock
    private KorisnikRepository korisnikRepositoryMock;

    @InjectMocks
    private ZalbaService zalbaService;

    @Test
    public void testFindAllByPacijent(){
        Zalba z1 = new Zalba("zalba1", "tekst1", "marko", "21-3-2018");
        z1.setId(1);
        Zalba z2 = new Zalba("zalba2", "tekst2", "marko", "21-3-2018");
        z2.setId(2);
        Zalba z3 = new Zalba("zalba3", "tekst3", "nikola", "21-3-2018");
        z3.setId(3);
        Zalba z4 = new Zalba("zalba4", "tekst4", "marko", "21-3-2018");
        z4.setId(4);

        when(zalbaRepositoryMock.findById(z1.getId())).thenReturn(Optional.of(z1));
        when(zalbaRepositoryMock.findById(z2.getId())).thenReturn(Optional.of(z2));
        when(zalbaRepositoryMock.findById(z3.getId())).thenReturn(Optional.of(z3));
        when(zalbaRepositoryMock.findById(z4.getId())).thenReturn(Optional.of(z4));

        List<Zalba> lista = new ArrayList<>();
        lista.add(z1);
        lista.add(z2);
        lista.add(z3);
        when(zalbaRepositoryMock.findAllByPacijent("marko")).thenReturn(lista);
        assertEquals(3, zalbaRepositoryMock.findAllByPacijent("marko").size());
    }
}
