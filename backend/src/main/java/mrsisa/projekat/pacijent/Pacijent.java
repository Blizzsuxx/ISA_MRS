package mrsisa.projekat.pacijent;

import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.tipPenala.Penal;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "pacijenti")
@Proxy(lazy = false)
@PrimaryKeyJoinColumn(name = "korisnik")
public class Pacijent extends Korisnik {
	@Column(name="broj_poena")
	private int brojPoena;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Erecept> eRecepti;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Erecept> izdatiPrekoERecepta;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Rezervacija> rezervacije;

	@ManyToOne(fetch = FetchType.EAGER)
	public Adresa adresa;

	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Lijek> alergije;
	@JsonIgnore
    @OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Penal> penali;

	@Column(name="kategorija")
	private String kategorija;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ocena> ocene;

    @OneToMany(fetch = FetchType.LAZY)
	private List<Apoteka> pretplata;

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

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	public List<Apoteka> getPretplata() {
		return pretplata;
	}

	public void setPretplata(List<Apoteka> pretplata) {
		this.pretplata = pretplata;
	}
}
