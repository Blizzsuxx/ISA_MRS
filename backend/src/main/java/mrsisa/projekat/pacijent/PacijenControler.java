package mrsisa.projekat.pacijent;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/profil")
public class PacijenControler {
	
	 private final PacijentService pacijentService;
	    @Autowired
	    public PacijenControler(PacijentService pacijentService){
	        this.pacijentService = pacijentService;
	    }


		@GetMapping(path="/dobaviPacijenta")
	    public Pacijent dobaviPacijenta(){
	    	//System.out.println(pacijentService.dobaviPacijenta().getFirstName());
	    	return pacijentService.dobaviPacijenta();
	    }

	@PutMapping("/izmeni")
	public boolean izmeni(@RequestBody List<String> info) {
		return pacijentService.promeni(info);
	}

	@GetMapping(path="/dobaviPacijente")
	public List<Pacijent> dobaviPacijentee(){
		System.out.println(pacijentService.dobaviPacijenta().getFirstName());
		return pacijentService.dobaviPacijente();
	}

}
