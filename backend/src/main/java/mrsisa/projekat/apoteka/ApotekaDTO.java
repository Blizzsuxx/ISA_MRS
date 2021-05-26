package mrsisa.projekat.apoteka;

import mrsisa.projekat.adresa.Adresa;

public class ApotekaDTO {
    private Long id;
    private String ime;
    private String mjesto;
    private String ptt;
    private String ulica;
    private String broj;
    private double duzina;
    private double sirina;

    public ApotekaDTO() {

    }

    public ApotekaDTO(Long id, String ime){
        this.ime = ime;
        this.id = id;
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
}
