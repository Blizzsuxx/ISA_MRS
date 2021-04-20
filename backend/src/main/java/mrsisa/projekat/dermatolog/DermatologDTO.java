package mrsisa.projekat.dermatolog;

import java.time.LocalDateTime;

public class DermatologDTO {
    private Integer id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;
    private LocalDateTime rodjendan;
    public DermatologDTO(){

    }
    public DermatologDTO(Dermatolog dermatolog){
        this.ime = dermatolog.getFirstName();
        this.prezime   = dermatolog.getLastName();
        this.korisnickoIme = dermatolog.getUsername();
        this.email = dermatolog.getEmail();
        this.rodjendan = dermatolog.getBirthday();
        this.id = dermatolog.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRodjendan() {
        return rodjendan;
    }

    public void setRodjendan(LocalDateTime rodjendan) {
        this.rodjendan = rodjendan;
    }
}
