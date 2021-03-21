package mrsisa.projekat.lijek;

public class Lijek {
    private int sifra;
    private String naziv;
    private String vrstaLijeka;
    private String oblikLijeka;
    private String sastav;
    private String proizvodjac;
    //private RezimIzdavanja rezimIzdavanja;
    private String napomena;

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrstaLijeka() {
        return vrstaLijeka;
    }

    public void setVrstaLijeka(String vrstaLijeka) {
        this.vrstaLijeka = vrstaLijeka;
    }

    public String getOblikLijeka() {
        return oblikLijeka;
    }

    public void setOblikLijeka(String oblikLijeka) {
        this.oblikLijeka = oblikLijeka;
    }

    public String getSastav() {
        return sastav;
    }

    public void setSastav(String sastav) {
        this.sastav = sastav;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Lijek() {

    }

    public Lijek(int sifra, String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
    }

    public Lijek(String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena) {
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
    }
}
