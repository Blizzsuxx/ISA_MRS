package mrsisa.projekat.narudzbenica;


import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/narudzbenice")
public class NarudzbenicaController {
    private final NarudzbenicaService narudzbenicaService;
    @Autowired
    public NarudzbenicaController(NarudzbenicaService narudzbenicaService){
        this.narudzbenicaService = narudzbenicaService;
    }
    @PostMapping(value="/kreirajNarudzbenicu",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void kreirajNarudzbenicu( Map<String,String> json){
        System.out.println(json.get("lijekovi"));
    }
}
