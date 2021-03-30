package mrsisa.projekat.korisnik;

public class KorisnikDTO {
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String rodjendan;

    public KorisnikDTO() {}

    public KorisnikDTO(String korisnickoIme, String sifra, String ime, String prezime, String rodjendan) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.rodjendan = rodjendan;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

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

    public String getRodjendan() {
        return rodjendan;
    }

    public void setRodjendan(String rodjendan) {
        this.rodjendan = rodjendan;
    }
}
