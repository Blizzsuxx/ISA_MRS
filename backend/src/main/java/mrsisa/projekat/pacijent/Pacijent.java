package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacijenti")
@PrimaryKeyJoinColumn(name = "korisnik")
public class Pacijent extends Korisnik {
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Erecept> eRecepti;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Erecept> izdatiPrekoERecepta;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Rezervacija> rezervacije;

	@ManyToOne(fetch = FetchType.EAGER)
	public Adresa adresa;

	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lijek> alergije;
	
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) { this.rezervacije = rezervacije;}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public List<Lijek> getAlergije() {
		return alergije;
	}

	public List<Erecept> getIzdatiPrekoERecepta() {
		return izdatiPrekoERecepta;
	}

	public void setIzdatiPrekoERecepta(List<Erecept> izdatiPrekoERecepta) {
		this.izdatiPrekoERecepta = izdatiPrekoERecepta;
	}

	public Pacijent() {}
	public Pacijent(String name, String username, String password, String lastname, LocalDateTime birthday){
		this.setFirstName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setBirthday(birthday);
		this.setLastName(lastname);
		this.setAlergije(new ArrayList<Lijek>());
		this.seteRecepti(new ArrayList<>());
		this.setRezervacije(new ArrayList<>());
	}

	public void setAlergije(List<Lijek> alergije) {
		this.alergije = alergije;
	}

	public Pacijent(List<Erecept> eRecepti) {
		this.eRecepti = eRecepti;
	}

	public List<Erecept> geteRecepti() {
		return eRecepti;
	}

	public void seteRecepti(List<Erecept> eRecepti) {
		this.eRecepti = eRecepti;
	}
}
