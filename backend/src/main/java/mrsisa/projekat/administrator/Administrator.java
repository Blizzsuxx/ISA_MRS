package mrsisa.projekat.administrator;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "administratori")
@PrimaryKeyJoinColumn(name = "korisnik")
public class Administrator extends Korisnik {
    public Administrator() {}

    public Administrator(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
    }

    public Administrator(KorisnikDTO dummy) {
        super(dummy);
    }

    @Override
    public Apoteka orElse(Object o) {
        return null;
    }
}
