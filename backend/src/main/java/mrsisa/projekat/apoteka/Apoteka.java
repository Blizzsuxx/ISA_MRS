package mrsisa.projekat.apoteka;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.lijek.StanjeLijeka;

import javax.persistence.*;
import java.util.List;

@Entity
public class Apoteka {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Adresa adresa;
    @Column(name = "ime", nullable = false)
    private String ime;
    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> lijekovi;


    public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	public Apoteka(){

    }
    public Apoteka(int id, String ime,List<StanjeLijeka> lijekovi){
        this.id = id;
        this.ime  = ime;
        this.lijekovi = lijekovi;
    }
    public Apoteka(String ime,List<StanjeLijeka> lijekovi){
        this.ime  =  ime;
        this.lijekovi = lijekovi;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
