package mrsisa.projekat.zalba;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="zalbe")
public class Zalba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naslov", nullable = false)
    private String naslov;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="pacijent")
    private String pacijent;

    @Column(name="datum_vrijeme")
    private String datumVrijeme;

    @OneToMany(mappedBy = "zalba", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Odgovor> odgovori;

    public Zalba() {}

    public Zalba(String naslov, String text, String pacijent, String datumVrijeme) {
        this.naslov = naslov;
        this.text = text;
        this.pacijent = pacijent;
        this.datumVrijeme = datumVrijeme;
    }

    public Zalba(String naslov, String text, String pacijent, String datumVrijeme, List<Odgovor> odgovori) {
        this.naslov = naslov;
        this.text = text;
        this.pacijent = pacijent;
        this.datumVrijeme = datumVrijeme;
        this.odgovori = odgovori;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }

    public String getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(String datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public List<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }
}
