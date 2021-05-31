package mrsisa.projekat.pacijent;

import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.apoteka.ApotekaRepository;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.dermatolog.DermatologDTO;
import mrsisa.projekat.dermatolog.DermatologRepository;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.erecept.EreceptDTO;
import mrsisa.projekat.erecept.EreceptRepository;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.farmaceut.FarmaceutRepository;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekRepository;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.ocena.OcenaDTO;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.popust.Popust;
import mrsisa.projekat.popust.PopustRepository;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.poseta.PosetaRepository;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.rezervacija.RezervacijaDTO;
import mrsisa.projekat.rezervacija.RezervacijaRepository;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.tipPenala.Penal;
import mrsisa.projekat.tipPenala.PenalRepository;
import mrsisa.projekat.uloga.Uloga;
import mrsisa.projekat.uloga.UlogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

import mrsisa.projekat.adresa.Adresa;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PacijentService {
	private final PacijentRepository pacijentRepository;
	private final UlogaRepository ulogaRepository;
	private final PosetaRepository posetaRepository;
	private final DermatologRepository dermatologRepository;
	private final FarmaceutRepository farmaceutRepository;
	private final LijekRepository lekRepository;
	private final EreceptRepository ereceptRepository;
	private final RezervacijaRepository rezervacijaRepository;
	private final ApotekaRepository apotekaRepository;
	private final PopustRepository popustRepository;
	private final PenalRepository penalRepository;
	@Autowired
	public PacijentService(PacijentRepository pacijentRepository, UlogaRepository ulogaRepository, PosetaRepository posete,
						   FarmaceutRepository f, DermatologRepository d, LijekRepository lek, PopustRepository popustR,
						   EreceptRepository ereceptR, RezervacijaRepository rezervacijaRepository, ApotekaRepository apotekaR,
						   PenalRepository penal){
		this.pacijentRepository = pacijentRepository;
		this.ulogaRepository = ulogaRepository;
		this.posetaRepository=posete;
		this.dermatologRepository=d;
		this.farmaceutRepository=f;
		this.lekRepository=lek;
		this.ereceptRepository=ereceptR;
		this.rezervacijaRepository=rezervacijaRepository;
		this.apotekaRepository=apotekaR;
		this.popustRepository=popustR;
		this.penalRepository=penal;
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

	@Transactional
	public PacijentDTO dobaviPacijenta(){
		Pacijent p=pacijentRepository.findOneById(9);

		PacijentDTO dto=new PacijentDTO(p);
		return dto;

	}

	public boolean promeni(List<String> podaci) { //pazi, uvek salji sve, i to istim redom
		//[Zarko, Kida pasa, Novi Sad, Jevrejska, 2a, zarkoKisa@gmail.com, datum] fali datum rodjenja i pol TODO i ako se promeni ulica mora i geo sirina ANDRIJA
		Pacijent p=pacijentRepository.findByEmail(podaci.get(5));
		p.setFirstName(podaci.get(0));
		p.setLastName(podaci.get(1));
		p.getAdresa().setMesto(podaci.get(2));
		p.getAdresa().setUlica(podaci.get(3));
		p.getAdresa().setBroj(podaci.get(4));
		pacijentRepository.save(p);
		return true;
	}
	@Transactional
	public List<RezervacijaDTO> dobaviRezervacije() {
		//TODO: dodaj id preko kog treba da se dobavi korisnik

		List<Rezervacija> sveRezervacije=this.rezervacijaRepository.findAllByUserId(9);//TODO 9
		List<RezervacijaDTO> dto=new ArrayList<>();
		for(Rezervacija r : sveRezervacije){
			if(r.getDatumRezervacije().isAfter(LocalDateTime.now())){
			for(StanjeLijeka s : r.getRezervisaniLijekovi()){

				dto.add(new RezervacijaDTO(r,s));}}
		}
		return dto;

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
	@Transactional
    public List<EreceptDTO> dobaviERecepteIzdate() {
		List<Erecept> sviRecepti=this.ereceptRepository.findAllByUserID(9); //TODO pazi 9
		List<EreceptDTO> trazeniRecepti=new ArrayList<>();
		for(Erecept e : sviRecepti){
			if(e.isIzdato()){
				trazeniRecepti.add(new EreceptDTO(e,1));
			}

		}
		return trazeniRecepti;

		/*Pacijent p=new Pacijent();//dobaviPacijenta();
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
		//return findOne("pera1").getIzdatiPrekoERecepta();*/

    }
	@Transactional
	public List<EreceptDTO> dobaviERecepte() {
		Pacijent p=new Pacijent();//dobaviPacijenta();
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
	@Transactional
	public void izbaciAlergije(List<Lijek> dummy) {

		 Pacijent p=this.pacijentRepository.findOneById(9); //TODO 9 nemoj molim te
		 for(Lijek l : dummy){
		 	for(Lijek a : p.getAlergije()){
		 		if(a.getId().equals(l.getId())){p.getAlergije().remove(a); break;}
			}
		 }
		 this.pacijentRepository.save(p);
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

	@Transactional
	public List<Penal> dobaviPenale() {
		//Pacijent p=this.pacijentRepository.findOneById(9); //todo 9
		List<Penal> penali=this.penalRepository.findAllByIdPacijent(9+"");
		return penali;
	}
	@Transactional
    public String dobaviKategoriju() {
		//TODO 9
		Pacijent p=this.pacijentRepository.findOneById(9); //TODO 9
		Popust popust=this.popustRepository.findById(1); //postoji samo 1 //TODO proveri
		String tekst="";
		if(popust.getOdRegular()<=p.getBrojPoena() && popust.getDoRegular()>p.getBrojPoena())
		{
			tekst+="Pripadate REGULAR kategoriji.\n";
		}else if(popust.getOdSilver()<=p.getBrojPoena() && popust.getDoSilver()>p.getBrojPoena())
		{
			tekst+="Pripadate SILVER kategoriji.\n";
		}else
		{
			tekst+="Pripadate GOLD kategoriji.\n ";
		}
		tekst+=" Sa tom kategorijom ostvarujete popust od "+popust.getPopustRegular()
				+"% na svaku rezervaciju leka, pregled kod dermatologa i farmaceuta.\n\nTrenutno imate: "+p.getBrojPoena()+" poena.";


		return tekst;
    }

    @Transactional
    public List<ApotekaDTO> dobaviPretplatu() {
		List<Apoteka> apoteke=this.pacijentRepository.findOneById(9).getPretplata(); //todo 9
		List<ApotekaDTO> apo=new ArrayList<>();
		for(Apoteka a : apoteke){
			apo.add(new ApotekaDTO(a));
		}
        return apo;
    }

    @Transactional
    public List<OcenaDTO> dobaviSvojeDermatologe() {
		List<Poseta> svePosete= this.posetaRepository.findAllByPacijentId(9);
		HashMap<Integer, OcenaDTO> ocene=new HashMap<>();
		List<OcenaDTO> dermatolozi=new ArrayList<>();

		for(Poseta p : svePosete){
			if(p.getRadnik()!=null){
			Dermatolog d=this.dermatologRepository.findByIdD(p.getRadnik().getId());
			if(d!=null) {
				for (Ocena o : ((Dermatolog) d).getOcene()) {

					if (o.getPacijent().getId() == 9) { //TODO ovde dobaviti id pacijenta, tj poslati kao param
						ocene.put(p.getRadnik().getId(), new OcenaDTO(o, p.getRadnik()));
						//dermatolozi.add(new OcenaDTO(o, p.getRadnik()));
						break;
					}
				}
				if (d.getOcene().size() == 0) {
					OcenaDTO dermOcena = new OcenaDTO(d);
					ocene.put(p.getRadnik().getId(), dermOcena);
					//dermatolozi.add(dermOcena);
				}
			}
			}
		}
		for(OcenaDTO o: ocene.values()){
			dermatolozi.add(o);
		}
		//System.out.println(dermatolozi.size());
		return dermatolozi;

    }
    @Transactional
	public List<OcenaDTO> dobaviSvojeFarmaceute() {
		List<Poseta> svePosete= this.posetaRepository.findAllByPacijentId(9);
		List<OcenaDTO> farmaceuti=new ArrayList<>();
		HashMap<Integer, OcenaDTO> ocene=new HashMap<>();
		for(Poseta p : svePosete){
			if(p.getRadnik()!=null){
			Farmaceut f=this.farmaceutRepository.findByIdD(p.getRadnik().getId());
			if(f!=null) {
				for (Ocena o : ((Farmaceut) f).getOcene()) {
					if (o.getPacijent().getId() == 9) { //TODO ovde dobaviti id pacijenta, tj poslati kao param
						ocene.put(f.getId(),new OcenaDTO(o, p.getRadnik()));
						// farmaceuti.add(new OcenaDTO(o, p.getRadnik()));
						break;
					}
				}
				if (f.getOcene().size() == 0) {
					OcenaDTO dermOcena = new OcenaDTO(f);

					ocene.put(f.getId(),dermOcena);
					//farmaceuti.add(dermOcena);
				}
			}
			}
		}
		for(OcenaDTO o: ocene.values()){
			farmaceuti.add(o);
		}
		return farmaceuti;

	}
	@Transactional
	public List<OcenaDTO> dobaviSvojeApoteke() {
		List<Poseta> svePosete= this.posetaRepository.findAllByPacijentId(9);
		HashMap<Long,OcenaDTO> apoteka=new HashMap<>();
		for(Poseta p : svePosete){
			if(p.getRadnik()!=null) {
				Farmaceut f = this.farmaceutRepository.findByIdD(p.getRadnik().getId());
				Dermatolog d = this.dermatologRepository.findByIdD(p.getRadnik().getId());
				if (f != null) {
					for (Ocena o : ((Farmaceut) f).getOcene()) {
						if (o.getPacijent() != null) {
							if (o.getPacijent().getId() == 9) {
								apoteka.put(p.getApoteka().getId(), new OcenaDTO(o, p.getApoteka()));
								break;
							}
						}
					}
					if (f.getOcene().size() == 0) {
						OcenaDTO dermOcena = new OcenaDTO(p.getApoteka());
						apoteka.put(p.getApoteka().getId(), dermOcena);
					}
				}


				if (d != null) {
					for (Ocena o : (d).getOcene()) {
						if (o.getPacijent() != null) {
							if (o.getPacijent().getId() == 9) {
								apoteka.put(p.getApoteka().getId(), new OcenaDTO(o, p.getApoteka()));
								break;
							}
						}
					}
					if (d.getOcene().size() == 0) {
						OcenaDTO dermOcena = new OcenaDTO(p.getApoteka());
						apoteka.put(p.getApoteka().getId(), dermOcena);
					}
				}
			}
		}

		List<Erecept> sviErecepti=this.ereceptRepository.findAllByUserID(9); //TODO pazi na 9
		for(Erecept e : sviErecepti){
			if(e.getPrepisaniLijekovi().get(0)!=null){
				if(e.getPrepisaniLijekovi().get(0).getApoteka()!=null){
					OcenaDTO ereceptOcena=new OcenaDTO(e.getPrepisaniLijekovi().get(0).getApoteka()); //ocena apoteke preko erecepta
					apoteka.put(e.getPrepisaniLijekovi().get(0).getApoteka().getId(),ereceptOcena);
				}
			}
		}
		List<Rezervacija> sveRezervacije=this.rezervacijaRepository.findAllByUserId(9);//TODO 9
		for(Rezervacija r : sveRezervacije){
			if(r.isIzdato() && r.getApoteka()!=null){
				OcenaDTO dto=new OcenaDTO(r.getApoteka());
				apoteka.put(r.getApoteka().getId(),dto);
			}

		}
		List<OcenaDTO> krajnjeOcene=new ArrayList<OcenaDTO>();
		for(OcenaDTO o : apoteka.values()){
			krajnjeOcene.add(o);
		}

		return krajnjeOcene;
	}

	@Transactional  //ovde se prikupe sve ocene za sve lekove koje je koristio pacijent
	public List<OcenaDTO> dobaviSvojeLekove(){
		List<OcenaDTO> lekoviOcena=new ArrayList<>();
		HashMap<Long, OcenaDTO> ocene=new HashMap<>();
		List<Erecept> sviErecepti=this.ereceptRepository.findAllByUserID(9); //TODO pazi na 9
		for(Erecept e : sviErecepti){
			for(StanjeLijeka s : e.getPrepisaniLijekovi()){
				if(s.getLijek()!=null){
				OcenaDTO ereceptOcena=new OcenaDTO(s.getLijek()); //ocena apoteke preko erecepta
				ocene.put(s.getLijek().getId(), ereceptOcena);
				}
			}
		}
		List<Rezervacija> sveRezervacije=this.rezervacijaRepository.findAllByUserId(9);//TODO 9
		for(Rezervacija r : sveRezervacije){
			for(StanjeLijeka s : r.getRezervisaniLijekovi()){
				if(s.getLijek()!=null) {
					OcenaDTO dto = new OcenaDTO(s.getLijek());
					ocene.put(s.getLijek().getId(), dto);
					}
				}
			}

		for(OcenaDTO o: ocene.values()){
			lekoviOcena.add(o);
		}
		return lekoviOcena;
	}

	@Transactional
	public void posaljiOcenu(String id) {
		//System.out.println(id);
		//System.out.println("bela vila");
		String podela[]=id.split("a");
		Ocena o=new Ocena();
		o.setOcena(Integer.parseInt(podela[1].trim()));
		if(id.startsWith("D")){
			//setovati korisnika!! Todo
			String novaP[]=podela[0].split("D");
			Dermatolog d= this.dermatologRepository.findByIdD(Integer.parseInt(novaP[1].trim()));
			d.getOcene().add(o);//korisniku setovati ocenu
			System.out.println(o.getOcena()+" jana");
			this.dermatologRepository.save(d);
			return;
		}else if(id.startsWith("F")){
			String novaP[]=podela[0].split("F");
			Farmaceut d= this.farmaceutRepository.findByIdD(Integer.parseInt(novaP[1].trim()));
			d.getOcene().add(o);//korisniku setovati ocenu i save
			System.out.println(o.getOcena()+" jana");
			this.farmaceutRepository.save(d);
			return;
		}else if(id.startsWith("A")){
			String novaP[]=podela[0].split("A");
			Apoteka d= this.apotekaRepository.findOneById(Integer.parseInt(novaP[1].trim()));
			d.getOcene().add(o);//korisniku setovati ocenu i save
			System.out.println(o.getOcena()+" jana");
			this.apotekaRepository.save(d);
			return;
		}else if(id.startsWith("L")){
			String novaP[]=podela[0].split("L");
			System.out.println(novaP[1].trim());
			Lijek d= this.lekRepository.findOneById(Long.parseLong(novaP[1].trim()));
			d.getOcene().add(o);//korisniku setovati ocenu i save
			System.out.println(o.getOcena()+" jana");
			this.lekRepository.save(d);
			return;
		}
	}
	@Transactional
	public void dodajAlergije(List<Lijek> info) {
		Pacijent p=this.pacijentRepository.findOneById(9); //TODO promeni 9
		List<Lijek> lekovi=lekRepository.findAll();
		List<Lijek> alergije=new ArrayList<>();
		for(Lijek l : lekovi){
			for(Lijek i :info) {
				if (l.getId() == i.getId()) {
					alergije.add(l);
					break;
				}

			}
		}
		p.setAlergije(alergije);
		System.out.println(p.getAlergije()+"boka");
		this.pacijentRepository.save(p);
	}

	@Transactional
	public boolean otkaziPretplatu(String id, Pacijent p) {
		Pacijent pacijent = this.pacijentRepository.findByUsername(p.getUsername()); //TODO 9
		for(Apoteka a: pacijent.getPretplata()){
			if((a.getId()+"").equals(id)){
				pacijent.getPretplata().remove(a);
				a.getPretplaceniPacijenti().remove(pacijent);
				return true;
			}
		}
		return false;


	}

	@Transactional //PAZIIII broj je 1 za lek 2 za savetovanje 3 za pregled
	public void nijePreuzeolek(Integer id,int br){ //ili LONG
		Pacijent p=this.pacijentRepository.findOneById(id);
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String dan=LocalDateTime.now().format(df);
		LocalDateTime istice=LocalDateTime.now().plusMonths(1);
		LocalDateTime pocetakmeseca=istice.withDayOfMonth(1);

		String opis="";
		if(br==1){
			opis="Korisnik nije preuzeo lek dana: "+dan;
		}else  if(br==2){
			opis="Korisnik nije dosao na savetovanje dana: "+dan;
		}else{
			opis="Korisnik nije dosao na pregled dana: "+dan;
		}
		Penal penal=new Penal(dan, opis, pocetakmeseca);
		penal.setIdKorisnika(id+"");
		p.getPenali().add(penal);
		this.pacijentRepository.save(p);
	//	this.penalRepository.save(penal);
	}



}
