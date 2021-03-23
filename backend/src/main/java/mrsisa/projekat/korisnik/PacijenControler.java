package mrsisa.projekat.korisnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaService;
import mrsisa.projekat.lijek.StanjeLijeka;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/profil")
public class PacijenControler {
	
	 private final PacijentService pacijentService;
	    @Autowired
	    public PacijenControler(PacijentService pacijentService){
	        this.pacijentService = pacijentService;
	    }


	    @GetMapping()
	    public Pacijent dobaviPacijenta(){
	    	return pacijentService.dobaviPacijenta();
	    }

}
