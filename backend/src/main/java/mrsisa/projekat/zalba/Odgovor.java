package mrsisa.projekat.zalba;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="odgovori")
public class Odgovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naslov", nullable = false)
    private String naslov;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="administrator")
    private String administrator;

    @Column(name="datum_vrijeme")
    private LocalDateTime datumVrijeme;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Zalba zalba;

    public Odgovor() {}

    public Odgovor(String naslov, String text, String administrator, LocalDateTime datumVrijeme) {
        this.naslov = naslov;
        this.text = text;
        this.administrator = administrator;
        this.datumVrijeme = datumVrijeme;
    }

    public Odgovor(String naslov, String text, String administrator, LocalDateTime datumVrijeme, Zalba zalba) {
        this.naslov = naslov;
        this.text = text;
        this.administrator = administrator;
        this.datumVrijeme = datumVrijeme;
        this.zalba = zalba;
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

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public Zalba getZalba() {
        return zalba;
    }

    public void setZalba(Zalba zalba) {
        this.zalba = zalba;
    }
}
