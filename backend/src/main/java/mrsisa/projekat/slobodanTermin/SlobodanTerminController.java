package mrsisa.projekat.slobodanTermin;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/slobodanTermin")
public class SlobodanTerminController {
    final private SlobodanTerminService slobodanTerminService;


    public SlobodanTerminController(SlobodanTerminService slobodanTerminService){
        this.slobodanTerminService = slobodanTerminService;
    }
}
