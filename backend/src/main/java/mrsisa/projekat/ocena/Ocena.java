package mrsisa.projekat.ocena;

import mrsisa.projekat.pacijent.Pacijent;

import javax.persistence.*;

@Entity
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Pacijent pacijent;
    @Column(name = "ocena", nullable = false)
    private int ocena;

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Ocena() {
    }

    public Ocena(Pacijent pacijent, int ocena) {
        this.pacijent = pacijent;
        this.ocena = ocena;
    }
}
