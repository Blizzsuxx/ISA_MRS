package mrsisa.projekat.administratorApoteke;

import javax.persistence.*;

import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.narudzbenica.*;
import mrsisa.projekat.administrator.Administrator;
import mrsisa.projekat.apoteka.Apoteka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class AdministratorApoteke extends Administrator {


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Apoteka apoteka;

    public AdministratorApoteke() {}

    public AdministratorApoteke(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public AdministratorApoteke(KorisnikDTO dummy){
        this.setUsername(dummy.getKorisnickoIme());
        this.setPassword(dummy.getSifra());
        this.setFirstName(dummy.getIme());
        this.setLastName(dummy.getPrezime());
        this.setBirthday(LocalDateTime.parse(dummy.getRodjendan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }
}