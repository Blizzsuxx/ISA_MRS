package mrsisa.projekat.korisnik;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;

import javax.persistence.Column;
import java.time.format.DateTimeFormatter;

public class KorisnikDTO {
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String rodjendan;
    private String email;
    private String uloga;
    private String ulica;
    private String broj;
    private String mesto;
    private String ptt;
    private String drzava;
    private String brojTelefona;

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
        this.broj = k.getBroj();
        this.mesto = k.getMesto();
        this.ptt = k.getPtt();
        this.drzava = k.getDrzava();
        this.brojTelefona = k.getBrojTelefona();
    }

    public KorisnikDTO(String korisnickoIme, String sifra, String ime, String prezime, String rodjendan,
                       String email, String uloga, String ulica, String broj, String mesto, String ptt,
                       String drzava, String brojTelefona) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.rodjendan = rodjendan;
        this.email = email;
        this.uloga = uloga;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.ptt = ptt;
        this.drzava = drzava;
        this.brojTelefona = brojTelefona;
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

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
}
