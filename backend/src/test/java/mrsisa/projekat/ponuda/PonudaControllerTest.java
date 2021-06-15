package mrsisa.projekat.ponuda;


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

@WithMockUser(username="mika1", roles= {"DOBAVLJAC"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class PonudaControllerTest {
    private static final String URL_PREFIX = "/api/v1/ponuda";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testKreirajPonudu() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "mika1", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        PonudaDTO ponudaDTO = new PonudaDTO();
        ponudaDTO.setId(1L);
        ponudaDTO.setIdNarudzbenice(1L);
        ponudaDTO.setRokPonude("2022-12-12");
        String json = mapper.writeValueAsString(ponudaDTO);

        this.mockMvc.perform(post(URL_PREFIX + "/kreirajPonudu").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

    }

}
