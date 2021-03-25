package mrsisa.projekat.Akcija;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import mrsisa.projekat.lijek.Lijek;

@Entity
public class Akcija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;	
	@Column(name = "procenatPopusta", nullable = false)
 private int procenatPopusta;
	@Column(name = "lek", nullable = false)
 private Lijek lek;
	@Column(name = "opis", nullable = true)
 private String opis;
public int getProcenatPopusta() {
	return procenatPopusta;
}
public void setProcenatPopusta(int procenatPopusta) {
	this.procenatPopusta = procenatPopusta;
}
public Lijek getLek() {
	return lek;
}
public void setLek(Lijek lek) {
	this.lek = lek;
}
public String getOpis() {
	return opis;
}
public void setOpis(String opis) {
	this.opis = opis;
}
public Akcija(int procenatPopusta, Lijek lek, String opis) {
	super();
	this.procenatPopusta = procenatPopusta;
	this.lek = lek;
	this.opis = opis;
}
public Akcija() {
	super();
}
 
}
