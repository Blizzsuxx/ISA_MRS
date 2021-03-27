package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.rezervacija.Rezervacija;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Pacijent extends Korisnik {
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Erecept> eRecepti;

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Rezervacija> rezervacije;

	public Pacijent() {}

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
