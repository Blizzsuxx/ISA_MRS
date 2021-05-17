package mrsisa.projekat.zalba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="api/zalbe")
public class ZalbaController {
    private final ZalbaService zalbaService;

    @Autowired
    public ZalbaController(ZalbaService zalbaService){
        this.zalbaService = zalbaService;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajZalbu")
    public void sacuvajZalbu(@RequestBody Zalba z){
        this.zalbaService.save(z);
    }
}
