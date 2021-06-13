package mrsisa.projekat.zalba;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="odgovori")
public class Odgovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="administrator")
    private String administrator;

    @Column(name="datum_vrijeme")
    private String datumVrijeme;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Zalba zalba;

    public Odgovor() {}

    public Odgovor(String text, String administrator, String datumVrijeme) {
        this.text = text;
        this.administrator = administrator;
        this.datumVrijeme = datumVrijeme;
    }

    public Odgovor(String text, String administrator, String datumVrijeme, Zalba zalba) {
        this.text = text;
        this.administrator = administrator;
        this.datumVrijeme = datumVrijeme;
        this.zalba = zalba;
    }

    public Odgovor(OdgovorDTO o){
        this.text = o.getText();
        this.administrator = o.getKorisnickoIme();
        this.datumVrijeme = o.getDatumVrijeme();
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

    public String getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(String datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public Zalba getZalba() {
        return zalba;
    }

    public void setZalba(Zalba zalba) {
        this.zalba = zalba;
    }
}
