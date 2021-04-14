package mrsisa.projekat.lijek;

public class LijekDTO {
    private long id;
    private String naziv;
    private String vrstaLijeka;
    private String oblikLijeka;
    private String sastav;
    private String proizvodjac;
    private String napomena;
    private double ocijena;

    public LijekDTO() {}

    public LijekDTO(long id, String naziv, String vrstaLijeka) {
        this.id = id;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
    }

    public LijekDTO(long id, String naziv, String vrstaLijeka, double ocijena) {
        this.id = id;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.ocijena = ocijena;
    }

    public LijekDTO(String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena) {
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getOcijena() {
        return ocijena;
    }

    public void setOcijena(double ocijena) {
        this.ocijena = ocijena;
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
}
