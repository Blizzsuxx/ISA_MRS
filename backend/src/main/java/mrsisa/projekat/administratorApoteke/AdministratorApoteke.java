package mrsisa.projekat.administratorApoteke;

import javax.persistence.*;

import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.administrator.Administrator;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.uloga.Uloga;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "administratoriApoteke")
@PrimaryKeyJoinColumn(name = "administrator")
public class AdministratorApoteke extends Administrator {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Apoteka apoteka;

    public AdministratorApoteke() {}

    public AdministratorApoteke(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public AdministratorApoteke(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
    }

    public AdministratorApoteke(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, Apoteka apoteka) {
        super(username, password, firstName, lastName, email, birthday);
        this.apoteka = apoteka;
    }

    public AdministratorApoteke(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate) {
        super(username, password, firstName, lastName, email, birthday, role, enabled, lastPasswordResetDate);
    }

    public AdministratorApoteke(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate, List<Uloga> uloge) {
        super(username, password, firstName, lastName, email, birthday, role, enabled, lastPasswordResetDate, uloge);
    }

    public AdministratorApoteke(KorisnikDTO dummy) {
        super(dummy);
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }
}