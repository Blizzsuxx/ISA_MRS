package mrsisa.projekat.pacijent;

import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptDTO;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaDTO;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.tipPenala.Penal;
import mrsisa.projekat.uloga.Uloga;
import mrsisa.projekat.uloga.UlogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import mrsisa.projekat.adresa.Adresa;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacijentService {
	private final PacijentRepository pacijentRepository;
	private final UlogaRepository ulogaRepository;

	@Autowired
	public PacijentService(PacijentRepository pacijentRepository, UlogaRepository ulogaRepository){
		this.pacijentRepository = pacijentRepository;
		this.ulogaRepository = ulogaRepository;
	}

	public Pacijent findByUsername(String username){
		return this.pacijentRepository.findByUsername(username);
	}

	public Pacijent save(Pacijent p) {
		p.setUloge(this.ulogaRepository.findByName(p.getRole()));
		return this.pacijentRepository.save(p);
	}


	public List<Pacijent> findAll(){
		return pacijentRepository.findAll();
	}
    public Pacijent findOne(String id){return pacijentRepository.findOneByUsername(id);}
	//TODO ovde dodje kod svih metoda jos string sa kljucem nekim

	public Pacijent dobaviPacijenta(){
		//return findOne("zarko");
		Pacijent p=new Pacijent("zarko", "pera", "pera","pera", LocalDateTime.now());
		List<Lijek> lek=new ArrayList<>();
		Adresa a=new Adresa("mesto", "ptt", "ulica",  "45", 50,50);
		p.setAdresa(a);
		p.setEmail("pera@gmail.com");
		lek.add(new Lijek(
				1L,
				"Paracetamol",
				"Protiv bolova",
				"tableta",
				"ljiek",
				"Biofarm",
				"Lijek"
		));
		lek.add(new Lijek(
				2L,
				"Brufen",
				"Protiv bolova",
				"tableta",
				"lek",
				"Biofarm",
				"Lek"
		));
		lek.add(new Lijek(
				3L,
				"Buscopan",
				"Protiv bolova",
				"tableta",
				"lek",
				"Biofarm",
				"Lek"
		));
		p.setAlergije(lek);
		//System.out.println(findOne("pera1").getFirstName());
		return p;//findOne("pera1");*/
	}

	public boolean promeni(List<String> podaci) { //pazi, uvek salji sve, i to istim redom

		return true;
	}
	@Transactional
	public List<RezervacijaDTO> dobaviRezervacije() {
		//TODO: dodaj id preko kog treba da se dobavi korisnik
		Pacijent p=dobaviPacijenta();
		ArrayList<Rezervacija> rez=new ArrayList<>();
		Apoteka a=new Apoteka(1L, "Apoteka 1",null);
		ArrayList<StanjeLijeka> rezLek=new ArrayList<>();
		ArrayList<StanjeLijeka> rezLek2=new ArrayList<>();
		rezLek.add(new StanjeLijeka(p.getAlergije().get(0),1,false));
		rezLek.get(0).setCijena(1000);
		rezLek.add(new StanjeLijeka(p.getAlergije().get(1),2,false));
		rezLek.get(1).setCijena(500);

		rezLek2.add(new StanjeLijeka(p.getAlergije().get(0),1,false));
		rezLek2.get(0).setCijena(1000);
		rezLek2.add(new StanjeLijeka(p.getAlergije().get(2),2,false));
		rezLek2.get(1).setCijena(500);

		Rezervacija r= new Rezervacija(1L,p,a,rezLek, LocalDateTime.now());
		Rezervacija r2= new Rezervacija(2L,p,a,rezLek2, LocalDateTime.now());
		rez.add(r);
		rez.add(r2);

		p.setRezervacije(rez);
		List<RezervacijaDTO> dto=new ArrayList<>();

		for(Rezervacija reze : p.getRezervacije()){

			for(StanjeLijeka sl : reze.getRezervisaniLijekovi()){
				dto.add(new RezervacijaDTO(reze, sl));
			}
		}
		dto.get(0).setDatumVazenja("25.04.2021.");
		dto.get(1).setDatumVazenja("25.04.2021.");
		dto.get(2).setDatumVazenja("30.04.2021.");
		dto.get(3).setDatumVazenja("29.04.2021.");
		return dto;
		/*List<RezervacijaDTO> dto=new ArrayList<>();
		for(Rezervacija reze : findOne("zarko").getRezervacije()){

			for(StanjeLijeka sl : reze.getRezervisaniLijekovi()){
				dto.add(new RezervacijaDTO(reze, sl));
			}}

		return dto;*/

	}

	public List<Pacijent> dobaviPacijente(){
		Pacijent p1 = new Pacijent();
		p1.setFirstName("John");
		p1.setLastName("Titor");
		Pacijent p2 = new Pacijent();
		p2.setFirstName("Marko");
		p2.setLastName("Polo");
		return List.of( p1, p2);
		//return findAll();
		//
	}

    public List<EreceptDTO> dobaviERecepteIzdate() {

		Pacijent p=dobaviPacijenta();
		StanjeLijeka s1=new StanjeLijeka(p.getAlergije().get(0),1,false);
		StanjeLijeka s2=new StanjeLijeka(p.getAlergije().get(1),2,false);
		List<StanjeLijeka> sl=new ArrayList<>();
		sl.add(s1);
		sl.add(s2);

		Erecept rec=new Erecept();
		rec.setPacijent(p);
		rec.setDatumIzdavanja(LocalDateTime.now());
		rec.setSifra("1");
		rec.setPrepisaniLijekovi(sl);

		Erecept rec1=new Erecept();
		rec1.setPacijent(p);
		rec1.setDatumIzdavanja(LocalDateTime.now());
		rec1.setSifra("1");
		rec1.setPrepisaniLijekovi(sl);

		List<Erecept> li=new ArrayList<>();
		li.add(rec);
		li.add(rec1);

		List<EreceptDTO> dto=new ArrayList<>();
		dto.add(new EreceptDTO(rec));
		dto.add(new EreceptDTO(rec1));
		return dto;
		//return findOne("pera1").getIzdatiPrekoERecepta();

    }

	public List<EreceptDTO> dobaviERecepte() {
		Pacijent p=dobaviPacijenta();
		StanjeLijeka s1=new StanjeLijeka(p.getAlergije().get(0),1,false);
		StanjeLijeka s2=new StanjeLijeka(p.getAlergije().get(1),2,false);
		StanjeLijeka s3=new StanjeLijeka(p.getAlergije().get(2),7,false);
		List<StanjeLijeka> sl=new ArrayList<>();
		sl.add(s1);
		sl.add(s2);
		List<StanjeLijeka> sl1=new ArrayList<>();
		sl1.add(s1);
		sl1.add(s3);

		Erecept rec=new Erecept();
		rec.setPacijent(p);
		rec.setDatumIzdavanja(LocalDateTime.now());
		rec.setSifra("1");
		rec.setPrepisaniLijekovi(sl);

		Erecept rec1=new Erecept();
		rec1.setPacijent(p);
		rec1.setDatumIzdavanja(LocalDateTime.now());
		rec1.setSifra("2");
		rec1.setPrepisaniLijekovi(sl1);

		List<Erecept> li=new ArrayList<>();
		li.add(rec);
		li.add(rec1);
		List<EreceptDTO> dto=new ArrayList<>();
		dto.add(new EreceptDTO(rec));
		dto.add(new EreceptDTO(rec1));
		return dto;
		//return findOne("pera1").geteRecepti();

	}

	public void izbaciAlergije(List<Lijek> dummy) {
		//doabviti pacijenta, i izbaciti alergije, ove iz liste
       //Pacijent p=findOne(id);
		 Pacijent p=new Pacijent();
		 p=dobaviPacijenta();
		 for(Lijek l : dummy){
		 	for(Lijek a : p.getAlergije()){
		 		if(a.getId().equals(l.getId())){p.getAlergije().remove(a); break;}
			}
		 }
		 //save(p);
	}
	//TODO, mozda prilikom svakog logovanja korisnika da se prodje kroz sve njegove rezervacije i da se proveri da li su istekle??
	public void izbaciRezervaciju(RezervacijaDTO stanje) { //potrebno je poslati id pacijenta jos, pronaci ga u bazi, i proveriti da li je dovoljno samo po nazivu
		Pacijent p =new Pacijent();
		for(Rezervacija r : p.getRezervacije()){
			for(StanjeLijeka s : r.getRezervisaniLijekovi()){
				if(s.getLijek().getNaziv().equals(stanje.getNazivLeka()) && s.getApoteka().getIme().equals(stanje.getNazivApoteke())){
					p.getRezervacije().remove(s);break;
				}
			}
		}
		//save(p);
	}

	public List<Penal> dobaviPenale() {
		Pacijent p=dobaviPacijenta();
		List<Penal> penali=new ArrayList<>();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String dan=LocalDateTime.now().format(df);
		Penal p1=new Penal(dan, "Niste preuzeli recept");
		Penal p2=new Penal(dan, "Niste preuzeli recept");
		penali.add(p1);
		penali.add(p2);
		p.setPenali(penali);
		return penali;
	}

    public Kategorija dobaviKategoriju() {
		Kategorija k =new Kategorija(20,20, "GOLD");
		return k;
    }

    public List<ApotekaDTO> dobaviPretplatu() {
		List<ApotekaDTO> apo=new ArrayList<>();
		ApotekaDTO apoteka1=new ApotekaDTO("apoteka1","mesto","nesto","meseto","nana");
		ApotekaDTO apoteka2=new ApotekaDTO("apoteka2","mesto","nesto","meseto","nana");
		ApotekaDTO apoteka3=new ApotekaDTO("apoteka3","mesto","nesto","meseto","nana");
		apo.add(apoteka1);apoteka1.setId(1L);
		apo.add(apoteka2);apoteka2.setId(2L);
		apo.add(apoteka3);apoteka3.setId(3L);
		return apo;
    }
}
