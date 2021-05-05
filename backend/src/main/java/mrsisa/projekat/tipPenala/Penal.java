package mrsisa.projekat.tipPenala;

import javax.persistence.*;

@Entity
@Table(name = "penali")
public class Penal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="dan")
    private String dan;
    @Column(name="opis")
    private String opis;

    public Penal(String dan, String opis) {
        this.dan = dan;
        this.opis = opis;
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
}
