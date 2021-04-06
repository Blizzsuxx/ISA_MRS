package mrsisa.projekat.dermatolog;

import javax.persistence.*;

import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.radnik.Radnik;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dermatolozi")
@PrimaryKeyJoinColumn(name = "radnik")
public class Dermatolog extends Radnik{
    @Column(name = "iq", nullable = false)
    private String iq; // atribut koji sluzi da bi istestirali validnost baze

    public Dermatolog() {}

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String iq) {
        super(username, password, firstName, lastName, email, birthday);
        this.iq = iq;
    }

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, String iq) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori);
        this.iq = iq;
    }

    public Dermatolog(String iq) {
        this.iq = iq;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }
}