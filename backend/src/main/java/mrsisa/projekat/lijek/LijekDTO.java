package mrsisa.projekat.lijek;

public class LijekDTO {
    private long id;
    private String naziv;
    private String vrstaLijeka;
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
}
