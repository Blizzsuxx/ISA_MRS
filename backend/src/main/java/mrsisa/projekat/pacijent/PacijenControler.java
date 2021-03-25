package mrsisa.projekat.pacijent;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	    	System.out.println(pacijentService.dobaviPacijenta().getIme());
	    	return pacijentService.dobaviPacijenta();
	    }

}
