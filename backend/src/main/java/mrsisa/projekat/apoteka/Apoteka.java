package mrsisa.projekat.apoteka;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
import mrsisa.projekat.rezervacija.Rezervacija;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.util.List;

@Entity
public class Apoteka {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Adresa adresa;
    @Column(name = "ime", nullable = false)
    private String ime;
    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> lijekovi;

    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RadnoVrijeme> radnaVremena;

    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SlobodanTermin> slobodanTermin;

    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rezervacija> rezervacije;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Dermatolog> dermatolozi;

    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Farmaceut> farmaceuti;

    //@OneToMany(mappedBy = "akcija", fetch = FetchType.LAZY)
    //private List<Akcija> akcija;

    public List<Narudzbenica> getNarudzbenice() {
        return narudzbenice;
    }

    public void setNarudzbenice(List<Narudzbenica> narudzbenice) {
        this.narudzbenice = narudzbenice;
    }


    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Narudzbenica> narudzbenice;

    public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}



    public void postaviAdresuIzDTO(ApotekaDTO apotekaDTO) {
        this.adresa.setBroj(apotekaDTO.getBroj());
        this.adresa.setUlica(apotekaDTO.getUlica());
        this.adresa.setMesto(apotekaDTO.getMjesto());
        this.adresa.setPtt(apotekaDTO.getPtt());
    }
	public Apoteka(){

    }
    public Apoteka(Long id, String ime,List<StanjeLijeka> lijekovi){
        this.id = id;
        this.ime  = ime;
        this.lijekovi = lijekovi;
    }
    public Apoteka(String ime,List<StanjeLijeka> lijekovi){
        this.ime  =  ime;
        this.lijekovi = lijekovi;

    }

    public Apoteka(ApotekaDTO dummy){
        this.ime = dummy.getIme();
        //this.adresa = new Adresa(dummy.getMjesto(), dummy.getPtt(), dummy.getUlica(), dummy.getBroj(), 10, 10);
    }
    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public List<RadnoVrijeme> getRadnaVremena() {
        return radnaVremena;
    }

    public void setRadnaVremena(List<RadnoVrijeme> radnaVremena) {
        this.radnaVremena = radnaVremena;
    }

    public List<StanjeLijeka> getLijekovi() {
        return lijekovi;
    }

    public void setLijekovi(List<StanjeLijeka> lijekovi) {
        this.lijekovi = lijekovi;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SlobodanTermin> getSlobodanTermin() {
        return slobodanTermin;
    }

    public void setSlobodanTermin(List<SlobodanTermin> slobodanTermin) {
        this.slobodanTermin = slobodanTermin;
    }

    public Apoteka orElse(Object o) {
        return null;
    }

    public List<Dermatolog> getDermatolozi() {
        return dermatolozi;
    }

    public void setDermatolozi(List<Dermatolog> dermatolozi) {
        this.dermatolozi = dermatolozi;
    }

    public List<Farmaceut> getFarmaceuti() {
        return farmaceuti;
    }

    public void setFarmaceuti(List<Farmaceut> farmaceuti) {
        this.farmaceuti = farmaceuti;
    }

    /*public List<Akcija> getAkcija() {
        return akcija;
    }

    public void setAkcija(List<Akcija> akcija) {
        this.akcija = akcija;
    }*/
}
