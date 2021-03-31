package mrsisa.projekat.adresa;


import mrsisa.projekat.apoteka.Apoteka;

import javax.persistence.*;
import java.util.List;

@Entity
public class Adresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "mesto", nullable = false)
	public String Mesto;
	@Column(name = "ptt", nullable = false)
	public String ptt;
	@Column(name = "ulica", nullable = false)
	public String ulica;
	@Column(name = "broj", nullable = false)
	public String broj;
	@Column(name = "gDuzina", nullable = false)
	public double gDuzina;
	@Column(name = "gSirina", nullable = false)
	public double gSirina;
 	@OneToMany(mappedBy = "adresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Apoteka> apoteke;

	public String getMesto() {
		return Mesto;
	}
	public void setMesto(String mesto) {
		Mesto = mesto;
	}
	public String getPtt() {
		return ptt;
	}
	public void setPtt(String ptt) {
		this.ptt = ptt;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	public double getgDuzina() {
		return gDuzina;
	}
	public void setgDuzina(double gDuzina) {
		this.gDuzina = gDuzina;
	}
	public double getgSirina() {
		return gSirina;
	}
	public void setgSirina(double gSirina) {
		this.gSirina = gSirina;
	}

	public List<Apoteka> getApoteke() {
		return apoteke;
	}

	public void setApoteke(List<Apoteka> apoteke) {
		this.apoteke = apoteke;
	}

	public Adresa(String mesto, String ptt, String ulica, String broj, double gDuzina, double gSirina, List<Apoteka> apoteke) {
		Mesto = mesto;
		this.ptt = ptt;
		this.ulica = ulica;
		this.broj = broj;
		this.gDuzina = gDuzina;
		this.gSirina = gSirina;
		this.apoteke = apoteke;
	}


	public Adresa(String mesto, String ptt, String ulica, String broj, double gDuzina, double gSirina) {
		super();
		Mesto = mesto;
		this.ptt = ptt;
		this.ulica = ulica;
		this.broj = broj;
		this.gDuzina = gDuzina;
		this.gSirina = gSirina;
	}
	public Adresa(double gDuzina, double gSirina) {
		super();
		this.gDuzina = gDuzina;
		this.gSirina = gSirina;
	}
	public Adresa(){

	}
	
	

}
