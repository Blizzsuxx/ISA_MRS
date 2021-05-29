package mrsisa.projekat.apoteka;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;

import java.util.ArrayList;
import java.util.List;

public class ApotekaDTO {
    private Long id;
    private String ime;
    private String mjesto;
    private String ptt;
    private String ulica;
    private String broj;
    private double duzina;
    private double sirina;
    private List<StanjeLijekaDTO> stanja;
    private double ukupnaCijena; // potrebno za Erecept
    private String rezultat; // potrebno za Erecept

    public ApotekaDTO() {

    }

    public ApotekaDTO(Long id, String ime){
        this.ime = ime;
        this.id = id;
    }

    public ApotekaDTO(Apoteka apoteka, double ukupnaCijena, String rezultat){
        this.id = apoteka.getId();
        this.ime = apoteka.getIme();
        this.mjesto = apoteka.getAdresa().getMesto();
        this.ptt = apoteka.getAdresa().getPtt();
        this.ulica = apoteka.getAdresa().getUlica();
        this.broj = apoteka.getAdresa().getBroj();
        this.stanja = new ArrayList<>();
        this.ukupnaCijena = ukupnaCijena;
        for (StanjeLijeka sl : apoteka.getLijekovi())
            this.stanja.add(new StanjeLijekaDTO(sl));
        this.rezultat = rezultat;
    }
    public ApotekaDTO(Apoteka apoteka){
        this.id = apoteka.getId();
        this.ime = apoteka.getIme();
        this.mjesto = apoteka.getAdresa().getMesto();
        this.ptt = apoteka.getAdresa().getPtt();
        this.ulica  = apoteka.getAdresa().getUlica();
        this.broj = apoteka.getAdresa().getBroj();
        this.duzina = apoteka.getAdresa().getgDuzina();
        this.sirina = apoteka.getAdresa().getgSirina();
    }
    public void setAdresa(Adresa adresa){
        this.mjesto = adresa.getMesto();
        this.ptt = adresa.getPtt();
        this.ulica = adresa.getUlica();
        this.broj  =  adresa.getBroj();
        this.sirina = adresa.getgSirina();
        this.duzina = adresa.getgDuzina();
    }

    public ApotekaDTO(String naziv, String mjesto, String ptt, String ulica, String broj) {
        this.ime = naziv;
        this.mjesto = mjesto;
        this.ptt = ptt;
        this.ulica = ulica;
        this.broj = broj;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDuzina() {
        return duzina;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public List<StanjeLijekaDTO> getStanja() {
        return stanja;
    }

    public void setStanja(List<StanjeLijekaDTO> stanja) {
        this.stanja = stanja;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }
}
