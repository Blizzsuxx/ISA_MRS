package mrsisa.projekat.tipPenala;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "penali")
public class Penal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dan")
    private String dan;
    @Column(name="opis")
    private String opis;
    @Column(name="traje_do")
    private LocalDateTime trajeDo;

    @Column(name="id_korisnika")
    private String idKorisnika;

    public Penal(String dan, String opis, LocalDateTime traje) {
        this.dan = dan;
        this.opis = opis;
        this.trajeDo=traje;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Penal() {
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getTrajeDo() {
        return trajeDo;
    }

    public void setTrajeDo(LocalDateTime trajeDo) {
        this.trajeDo = trajeDo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(String idKorisnika) {
        this.idKorisnika = idKorisnika;
    }
}
