package mrsisa.projekat.godisnjiOdmor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


@RunWith(SpringRunner.class)
@SpringBootTest
public class GodisnjiOdmorIntegracioniTest {
    private static final String URL_PREFIX = "/api/v1/GodisnjiOdmori";

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
    public void testPrihvatiGodisnji() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "dunja", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(put(URL_PREFIX + "/admin/odobriZahtjev/1").contentType(contentType))
                .andExpect(status().isOk())
                ;
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testOdbijGodisnji() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "dunja", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(put(URL_PREFIX + "/admin/odbijZahtjev/1").contentType(contentType))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testOdbijGodisnji1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "dunja", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(put(URL_PREFIX + "/admin/odbijZahtjev/4").contentType(contentType))
                .andExpect(status().isNotFound())
        ;
    }

}