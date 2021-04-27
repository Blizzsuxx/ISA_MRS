package mrsisa.projekat.korisnik;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;

import java.time.format.DateTimeFormatter;

public class KorisnikDTO {
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String rodjendan;
    private String email;
    private String uloga;

    public KorisnikDTO() {}

    public KorisnikDTO(String korisnickoIme, String sifra, String ime, String prezime, String rodjendan, String email) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.rodjendan = rodjendan;
        this.email = email;
    }

    public KorisnikDTO(String korisnickoIme, String sifra, String ime, String prezime, String rodjendan, String email, String uloga) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.rodjendan = rodjendan;
        this.email = email;
        this.uloga = uloga;
    }

    public KorisnikDTO(Korisnik k){
        this.korisnickoIme = k.getUsername();
        this.sifra = k.getPassword();
        this.ime = k.getFirstName();
        this.prezime = k.getLastName();
        this.rodjendan = k.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.email = k.getEmail();
        this.uloga =  k.getRole();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
