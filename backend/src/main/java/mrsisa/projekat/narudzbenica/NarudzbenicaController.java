package mrsisa.projekat.narudzbenica;


import com.fasterxml.jackson.databind.JsonNode;
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
    @PostMapping(value="/kreirajNarudzbenicu")
    public void kreirajNarudzbenicu( @RequestBody Map<String, Object> podaci){
        this.narudzbenicaService.kreirajNarudzbenicu(podaci);
    }
}
