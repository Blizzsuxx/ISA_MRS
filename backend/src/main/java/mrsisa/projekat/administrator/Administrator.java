package mrsisa.projekat.administrator;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.uloga.Uloga;

import java.sql.Timestamp;
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

    public Administrator(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate) {
        super(username, password, firstName, lastName, email, birthday, role, enabled, lastPasswordResetDate);
    }

    public Administrator(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate, List<Uloga> uloge) {
        super(username, password, firstName, lastName, email, birthday, role, enabled, lastPasswordResetDate, uloge);
    }
}
