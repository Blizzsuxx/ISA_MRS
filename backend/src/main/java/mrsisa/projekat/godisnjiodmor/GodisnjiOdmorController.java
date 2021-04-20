package mrsisa.projekat.godisnjiodmor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/godisnjiOdmor")
public class GodisnjiOdmorController {
    private final GodisnjiOdmorService  godisnjiOdmorService;

    @Autowired
    public GodisnjiOdmorController(GodisnjiOdmorService godisnjiOdmorService){
        this.godisnjiOdmorService = godisnjiOdmorService;
    }
    @GetMapping("/{id}")
    public GodisnjiOdmor dobaviGodisnjiOdmor(Long id){

        return godisnjiOdmorService.dobaviGodisnjiOdmor(id);
    }
}
