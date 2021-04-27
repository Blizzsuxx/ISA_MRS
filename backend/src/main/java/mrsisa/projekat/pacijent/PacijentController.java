package mrsisa.projekat.pacijent;

import mrsisa.projekat.bezbjednost.ResourceConflictException;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptDTO;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.RezervacijaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/profil")
public class PacijentController {
	private final PacijentService pacijentService;
	@Autowired
	public PacijentController(PacijentService pacijentService){
		this.pacijentService = pacijentService;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(path="/dobaviPacijenta")
	@PreAuthorize("hasRole('PACIJENT')")
	public Pacijent dobaviPacijenta(){
		//System.out.println(pacijentService.dobaviPacijenta().getFirstName());
		return pacijentService.dobaviPacijenta();
	}

	@PutMapping("/izmeni")
	@PreAuthorize("hasRole('PACIJENT')")
	public boolean izmeni(@RequestBody List<String> info) {
		return pacijentService.promeni(info);
	}

	@GetMapping(path="/dobaviPacijente")
	public List<Pacijent> dobaviPacijentee(){
		return pacijentService.dobaviPacijente();
	}

	@GetMapping(path="/dobaviIzdateERecepte")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<EreceptDTO> dobaviIzdateErecepte(){
		return pacijentService.dobaviERecepteIzdate();
	}

	@GetMapping(path="/dobaviRezervacije")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<RezervacijaDTO> dobaviRezervacije() {
		return pacijentService.dobaviRezervacije();
	}

	@GetMapping(path="/dobaviERecepte")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<EreceptDTO> dobaviERecepte(){
		//System.out.println("n");
		return pacijentService.dobaviERecepte();
	}

	@GetMapping(path="/promeniAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public boolean promeniAlergije(@RequestBody List<Lijek> info){
		if(info==null){
			return false;
		}else{return true;}
	}

	@PostMapping(consumes = "application/json", path = "/izbaciAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public void izbaciAlergije(@RequestBody List<Lijek> dummy) {
		pacijentService.izbaciAlergije(dummy);
	}

	@PostMapping("/registracija")
	public ResponseEntity<Pacijent> sacuvajPacijenta(@RequestBody KorisnikDTO dummy, UriComponentsBuilder ucBuilder){
		Korisnik k = this.pacijentService.findByUsername(dummy.getKorisnickoIme());
		if (k != null)
			throw new ResourceConflictException((long)k.getId(), "Username already exists");

		dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
		Pacijent p = this.pacijentService.save(new Pacijent(dummy));
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/izbaciRezervacije")
	public boolean izbRez( @RequestBody Map<String,Object> podaci){

		System.out.println(podaci.get("a").getClass().getName());

		return true;
	}
}
