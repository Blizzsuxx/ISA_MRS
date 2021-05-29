package mrsisa.projekat.lijek;

public class LijekDTO {
    private long id;
    private String sifra;
    private String naziv;
    private String vrstaLijeka;
    private String oblikLijeka;
    private String sastav;
    private String proizvodjac;
    private String napomena;
    private double ocijena;
    private int kolicina;
    private int poeni;

    public LijekDTO() {}

    public LijekDTO(Lijek l){
        this.id = l.getId();
        this.naziv = l.getNaziv();
        this.vrstaLijeka = l.getVrstaLijeka();
        this.oblikLijeka = l.getOblikLijeka();
        this.sastav = l.getSastav();
        this.proizvodjac = l.getProizvodjac();
        this.napomena = l.getNapomena();
        this.ocijena = l.getOcijena();
        this.poeni = l.getPoeni();
    }

    public LijekDTO(long id, String naziv, String vrstaLijeka) {
        this.id = id;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
    }

    public LijekDTO(long id, String naziv, String vrstaLijeka, double ocijena,String proizvodjac,String oblikLijeka) {
        this.id = id;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.ocijena = ocijena;
        this.proizvodjac = proizvodjac;
        this.oblikLijeka  = oblikLijeka;
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



    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
