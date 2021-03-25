package mrsisa.projekat.korisnik;

import mrsisa.projekat.adresa.Adresa;

public class Pacijent {
private String username, password, ime, prezime;
public String getIme() {
	return ime;
}

public void setIme(String ime) {
	this.ime = ime;
}

public String getPrezime() {
	return prezime;
}

public void setPrezime(String prezime) {
	this.prezime = prezime;
}

private Adresa adresa;



public Adresa getAdresa() {
	return adresa;
}

public void setAdresa(Adresa adresa) {
	this.adresa = adresa;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Pacijent(String username, String password,String ime,String prezime) {
	super();
	this.username = username;
	this.password = password;
}

}
