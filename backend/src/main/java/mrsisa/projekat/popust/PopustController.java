package mrsisa.projekat.popust;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping(path="api/popust")
public class PopustController {
    private final PopustService popustService;

    public PopustController(PopustService popustService){
        this.popustService = popustService;
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @PostMapping(consumes = "application/json", path = "/sacuvajPopust")
    public void sacuvajPopust(@RequestBody Popust dummy) {
        dummy.setId(1);
       this.popustService.save(dummy);
    }

    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    @GetMapping(produces = "application/json", path = "/dobaviPopust")
    public Popust dobaviPopust() {
        System.out.println("Objekat" + popustService.findById(1));
        return this.popustService.findById(1);
    }
}
