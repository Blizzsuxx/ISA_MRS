package mrsisa.projekat.dermatolog;

import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.ocena.Ocena;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DermatologDTO {
    private Integer id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;
    private String rodjendan;
    private List<String> apoteke;
    private double ocjena ;
    public DermatologDTO(){

    }
    public DermatologDTO(Dermatolog dermatolog){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ime = dermatolog.getFirstName();
        this.prezime   = dermatolog.getLastName();
        this.korisnickoIme = dermatolog.getUsername();
        this.email = dermatolog.getEmail();
        this.rodjendan = dermatolog.getBirthday().format(dtf);
        this.id = dermatolog.getId();
        this.apoteke =  new ArrayList<>();
        int suma = 0;
        for (Ocena ocena: dermatolog.getOcene()) {
            suma +=ocena.getOcena();
        }
        if(dermatolog.getOcene().size()!=0)
            this.ocjena =  suma/dermatolog.getOcene().size();
        else
            this.ocjena = -1;
    }
    public DermatologDTO(Farmaceut farmaceut){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ime = farmaceut.getFirstName();
        this.prezime   = farmaceut.getLastName();
        this.korisnickoIme = farmaceut.getUsername();
        this.email = farmaceut.getEmail();
        this.rodjendan = farmaceut.getBirthday().format(dtf);
        this.id = farmaceut.getId();
        this.apoteke =  new ArrayList<>();
        int suma = 0;
        for (Ocena ocena: farmaceut.getOcene()) {
            suma +=ocena.getOcena();
        }
        if(farmaceut.getOcene().size()!=0)
            this.ocjena =  suma/farmaceut.getOcene().size();
        else
            this.ocjena = -1;
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

    public String getRodjendan() {
        return rodjendan;
    }

    public void setRodjendan(String rodjendan) {
        this.rodjendan = rodjendan;
    }

    public List<String> getApoteke() {
        return apoteke;
    }

    public void setApoteke(List<String> apoteke) {
        this.apoteke = apoteke;
    }

    public double getOcjena() {
        return ocjena;
    }

    public void setOcjena(double ocjena) {
        this.ocjena = ocjena;
    }
}
