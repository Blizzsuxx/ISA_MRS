package mrsisa.projekat.zalba;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username="sulejman1", roles= {"ADMIN_SISTEMA"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdgovorControllerTest {
    private static final String URL_PREFIX = "/api/v1/odgovori";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testKreirajOdgovor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "sulejman1", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        OdgovorDTO odgovorDTO = new OdgovorDTO();
        odgovorDTO.setId(3);
        odgovorDTO.setKorisnickoIme("sulejman1");
        odgovorDTO.setText("nestoo");
        odgovorDTO.setDatumVrijeme("2021-07-13");
        String json = mapper.writeValueAsString(odgovorDTO);

        this.mockMvc.perform(post(URL_PREFIX + "/kreirajOdgovor").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        OdgovorDTO odgovorDTO2 = new OdgovorDTO();
        odgovorDTO.setId(10);
        String json2 = mapper.writeValueAsString(odgovorDTO);

        this.mockMvc.perform(post(URL_PREFIX + "/kreirajOdgovor").contentType(contentType).content(json2))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
