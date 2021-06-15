package mrsisa.projekat.zalba;

import mrsisa.projekat.util.MailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OdgovorServiceTest {

    @Mock
    private OdgovorRepository odgovorRepository;

    @Mock
    private ZalbaRepository zalbaRepository;

    @Mock
    private MailSender mailSenderMock;

    @InjectMocks
    private OdgovorService odgovorService;

    @Test
    public void testSacuvaj() throws IOException, MessagingException {
        Zalba z = new Zalba();
        z.setId(1);
        OdgovorDTO dto = new OdgovorDTO();
        dto.setId(1);
        dto.setText("aaa");
        dto.setKorisnickoIme("marko");
        dto.setDatumVrijeme("2019-01-01");

        when(zalbaRepository.findById(z.getId())).thenReturn(Optional.of(z));
        OdgovorDTO dto2 = new OdgovorDTO();
        dto2.setId(10);
        assertFalse(odgovorService.sacuvaj(dto2));

        Zalba z1= new Zalba();
        z1.setId(2);
        Odgovor o1 = new Odgovor();
        o1.setText("dsadsadsa");
        Odgovor o2 = new Odgovor();
        o2.setText("dassdaasd");
        z1.setOdgovori(new ArrayList<>());
        z1.getOdgovori().add(o1);
        z1.getOdgovori().add(o2);
        OdgovorDTO dto3 = new OdgovorDTO();
        dto3.setId(2);
        when(zalbaRepository.findById(z1.getId())).thenReturn(Optional.of(z1));
        assertFalse(odgovorService.sacuvaj(dto3));
    }
}
