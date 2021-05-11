package mrsisa.projekat.pacijent;

import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.tipPenala.Penal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "pacijenti")
@PrimaryKeyJoinColumn(name = "korisnik")
public class Pacijent extends Korisnik {
	@Column(name="broj_poena")
	private int brojPoena;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Erecept> eRecepti;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Erecept> izdatiPrekoERecepta;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Rezervacija> rezervacije;

	@ManyToOne(fetch = FetchType.EAGER)
	public Adresa adresa;

	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Lijek> alergije;

    @OneToMany(fetch =FetchType.LAZY)
	private List<Penal> penali;

    @OneToOne(fetch=FetchType.LAZY)
	private Kategorija kategorija;

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

	public List<Penal> getPenali() {
		return penali;
	}

	public void setPenali(List<Penal> penali) {
		this.penali = penali;
	}

	public void setIzdatiPrekoERecepta(List<Erecept> izdatiPrekoERecepta) {
		this.izdatiPrekoERecepta = izdatiPrekoERecepta;
	}

	public Pacijent() {}

	public Pacijent(KorisnikDTO dummy){
		super(dummy);
	}



	@Override
	public Apoteka orElse(Object o) {
		return null;
	}

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

	public int getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
}
