package mrsisa.projekat.qrkod;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mrsisa.projekat.apoteka.ApotekaDTO;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

@WithMockUser(username="zarko", roles= {"PACIJENT"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class QRKodControllerTest {
    private static final String URL_PREFIX = "/api/v1/qrkod";

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
    public void testFormirajErecept() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        ApotekaDTO apotekaDTO = new ApotekaDTO();
        apotekaDTO.setId(1L);

        apotekaDTO.setRezultat("1:2,2:3,3:1");
            //return mapper.writeValueAsString(object);
        String json = mapper.writeValueAsString(apotekaDTO);
        String poruka = "Kreiran erecept";

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "zarko", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(post(URL_PREFIX + "/kreirajErecept").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


    }

}
