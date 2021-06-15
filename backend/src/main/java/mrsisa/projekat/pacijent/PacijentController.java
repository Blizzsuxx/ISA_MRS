package mrsisa.projekat.pacijent;

import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.bezbjednost.ResourceConflictException;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptDTO;
import mrsisa.projekat.korisnik.ConfirmationToken;
import mrsisa.projekat.korisnik.ConfirmationTokenRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.ocena.OcenaDTO;
import mrsisa.projekat.rezervacija.RezervacijaDTO;
import mrsisa.projekat.tipPenala.Penal;
import mrsisa.projekat.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/profil")
public class PacijentController {
	private final PacijentService pacijentService;
	private final ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	public PacijentController(PacijentService pacijentService,
							  ConfirmationTokenRepository confirmationTokenRepository){
		this.pacijentService = pacijentService;
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(path="/dobaviPacijenta")
	@PreAuthorize("hasRole('PACIJENT')")
	public PacijentDTO dobaviPacijenta(){
		//System.out.println(pacijentService.dobaviPacijenta().getFirstName());
		return pacijentService.dobaviPacijenta();
	}

	@GetMapping(path="/dobaviKategoriju")
	@PreAuthorize("hasRole('PACIJENT')")
	public String dobaviKategoriju(){//umesto max poena vratiti moje trenutne poene
		return pacijentService.dobaviKategoriju();
	}

	@GetMapping(path="/pretplata")
	//@PreAuthorize("hasRole('PACIJENT')")
	public List<ApotekaDTO> pretplata(){//umesto max poena vratiti moje trenutne poene

		return pacijentService.dobaviPretplatu();
	}
	@PutMapping(path="/otkaziPretplatu")
	@PreAuthorize("hasRole('PACIJENT')")
	public boolean otkaziPretplatu(@RequestBody String id){//umesto max poena vratiti moje trenutne poene
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Pacijent p = (Pacijent)auth.getPrincipal();
		return  pacijentService.otkaziPretplatu(id, p);
	}

	@PostMapping(value="/proveriAlergije")
	public Boolean proveriAlergije(@RequestBody Map<String, Object> params){
		return this.pacijentService.proveriAlergije(params);
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



	@GetMapping(path="/dobaviPenale")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<Penal> dobaviPenale(){
		return pacijentService.dobaviPenale();
	}

	@GetMapping(path="/provariPenale")
	@PreAuthorize("hasRole('PACIJENT')")
	public boolean proveriPenale(){
		return pacijentService.proveriPenale();
	}

	@GetMapping(path="/dobaviSvojeDermatologe")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<OcenaDTO> dobaviSvojeDermatologe(){
		return pacijentService.dobaviSvojeDermatologe();
	}

	@GetMapping(path="/dobaviSvojeFarmaceute")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<OcenaDTO> dobaviSvojeFarmaceute(){
		return pacijentService.dobaviSvojeFarmaceute();
	}
	@GetMapping(path="/dobaviSvojeApoteke")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<OcenaDTO> dobaviSvojeApooteke(){
		return pacijentService.dobaviSvojeApoteke();
	}

	@GetMapping(path="/dobaviSvojeLekove")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<OcenaDTO> dobaviSvojeLekove(){
		return pacijentService.dobaviSvojeLekove();
	}

	@PutMapping(path="/posaljiOcenu/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public void posaljiOcenu(@PathVariable String id){
		pacijentService.posaljiOcenu(id);
	}

	@PostMapping(consumes = "application/json", path="/promeniAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public boolean promeniAlergije(@RequestBody List<Lijek> info){
		System.out.println(info);
		System.out.println(info.get(0)+"jasna");
		if(info==null){
			return false;
		}else{
			pacijentService.dodajAlergije(info);
			return true;}
	}

	@PostMapping(consumes = "application/json", path = "/izbaciAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public void izbaciAlergije(@RequestBody List<Lijek> dummy) {
		pacijentService.izbaciAlergije(dummy);
	}

	@PostMapping("/registracija")
	public void sacuvajPacijenta(@RequestBody KorisnikDTO dummy, UriComponentsBuilder ucBuilder)
	throws IOException, MessagingException {
		Korisnik k = this.pacijentService.findByUsername(dummy.getKorisnickoIme());
		if (k != null)
			throw new ResourceConflictException((long)k.getId(), "Username already exists");

		dummy.setSifra(passwordEncoder.encode(dummy.getSifra()));
		Pacijent p = new Pacijent(dummy);
		this.pacijentService.save(p);
		ConfirmationToken confirmationToken = new ConfirmationToken(p);
		confirmationTokenRepository.save(confirmationToken);

		MailSender.sendmail("Da bi ste potvrdili prijavu, kliknite na predloženi link : " +
						"http://localhost:8080/api/korisnici/potvrda-registracije?token="+confirmationToken.getConfirmationToken(),
				"dunjica.isa@gmail.com");

	}
	@RequestMapping(method = RequestMethod.POST, value = "/izbaciRezervacije")
	public boolean izbRez( @RequestBody Map<String,Object> podaci){
		System.out.println("carica milica");
		System.out.println(podaci.get("a").getClass().getName());

		return this.pacijentService.izbaciRezervaciju(podaci.get("a")+"");
	}

	@GetMapping(value="/dobaviKandidateZaZalbu/{tipZalbe}")
	@PreAuthorize("hasRole('PACIJENT')")
	public List<TempDTO> dobaviKandidateZaZalbu(@PathVariable String tipZalbe){
		return this.pacijentService.dobaviKandidateZaZalbu(tipZalbe);
	}
}
