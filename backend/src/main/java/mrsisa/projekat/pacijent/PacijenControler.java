package mrsisa.projekat.pacijent;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentService;
import mrsisa.projekat.rezervacija.RezervacijaDTO;
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

		return pacijentService.dobaviPacijente();
	}
	@GetMapping(path="/dobaviIzdateERecepte")
	public List<Erecept> dobaviIzdateErecepte(){

		return pacijentService.dobaviERecepteIzdate();
	}
	@GetMapping(path="/dobaviRezervacije")
	public List<RezervacijaDTO> dobaviRezervacije(){

		return pacijentService.dobaviRezervacije();
	}


	@GetMapping(path="/dobaviERecepte")
	public List<Erecept> dobaviERecepte(){
		System.out.println("n");
		return pacijentService.dobaviERecepte();
	}
	@GetMapping(path="/promeniAlergije")
	public boolean promeniAlergije(@RequestBody List<Lijek> info){
	    	if(info==null){
	    		return false;
			}else{return true;}
	}

	@PostMapping(consumes = "application/json", path = "/izbaciAlergije")
	public void izbaciAlergije(@RequestBody List<Lijek> dummy) {
		pacijentService.izbaciAlergije(dummy);

	}

}
