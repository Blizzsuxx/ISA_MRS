package mrsisa.projekat.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@WithMockUser(username="zarko", roles={"PACIJENT"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIntegracioniPacijent {

    private static final String URL_PREFIX = "/api/v1/profil";

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
    public void testIzmeni() throws Exception {

         List<String> podaci=new ArrayList<>();
        podaci.add("novo");
        podaci.add("ime");
        podaci.add("mesto");
        podaci.add("ulica");
        podaci.add("broj");
        podaci.add("zarkoKisa@gmail.com");
        podaci.add("2001-2-3T1");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(podaci);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "zarko", "123"));
        this.mockMvc.perform(put(URL_PREFIX + "/izmeni").contentType(contentType).content(json)
        ).andExpect(status().isOk());
               // .andExpect(content().string("true"));

    }

   /* @Test
    @Transactional
    @Rollback(true)
    public void testOceni() throws Exception {

        String ocena="D2a5";

        //String json = TestUtil.json(ocena); //TODO id
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString("D5a5");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "zarko", "123"));
        this.mockMvc.perform(put(URL_PREFIX + "/posaljiOcenu/{id}").contentType(contentType).content(json)
                )
                .andExpect(status().isCreated());
               // .andExpect(content().string(message));

    }*/
    @Test
    @Transactional
    @Rollback(true)
    public void testIstorija() throws Exception {

        String ocena="D2a5";

        //String json = TestUtil.json(ocena); //TODO id
        ObjectMapper mapper = new ObjectMapper();
       // String json = mapper.writeValueAsString("");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "zarko", "123"));
        this.mockMvc.perform(get("/api/v1/posete/dobaviIstorijuD").contentType(contentType)//.content(json)
        )
                .andExpect(status().isOk());
        // .andExpect(content().string(message));

    }
    @Test
    @Transactional
    @Rollback(true)
    public void testIstorijaFarm() throws Exception {

        String ocena="D2a5";

        //String json = TestUtil.json(ocena); //TODO id
        ObjectMapper mapper = new ObjectMapper();
        // String json = mapper.writeValueAsString("");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "zarko", "123"));
        this.mockMvc.perform(get("/api/v1/posete/dobaviIstorijuF").contentType(contentType)//.content(json)
        )
                .andExpect(status().isOk());
        // .andExpect(content().string(message));

    }

}
