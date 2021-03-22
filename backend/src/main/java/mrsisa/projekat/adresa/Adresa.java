package mrsisa.projekat.adresa;

public class Adresa {
	public String Mesto, ptt, ulica;
	public int broj;
	public double gDuzina, gSirina;
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
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
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
	public Adresa(String mesto, String ptt, String ulica, int broj, double gDuzina, double gSirina) {
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
	
	

}
