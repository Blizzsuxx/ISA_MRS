package mrsisa.projekat.zalba;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="zalbe")
public class Zalba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="naslov", nullable = false)
    private String naslov;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="pacijent", nullable = false)
    private String pacijent;

    @Column(name="datum_vrijeme", nullable = false)
    private String datumVrijeme;

    @Column(name="tip_zalbe", nullable = false) // dermatologa, farmaceuta, apoteku
    private String tipZalbe;

    @Column(name="id_objekta", nullable = false) // na sta se tuzi
    private String idObjekta;

    @JsonIgnore
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

    public Zalba(String naslov, String text, String pacijent, String datumVrijeme, String tipZalbe, String idObjekta, List<Odgovor> odgovori) {
        this.naslov = naslov;
        this.text = text;
        this.pacijent = pacijent;
        this.datumVrijeme = datumVrijeme;
        this.tipZalbe = tipZalbe;
        this.idObjekta = idObjekta;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipZalbe() {
        return tipZalbe;
    }

    public void setTipZalbe(String tipZalbe) {
        this.tipZalbe = tipZalbe;
    }

    public String getIdObjekta() {
        return idObjekta;
    }

    public void setIdObjekta(String idObjekta) {
        this.idObjekta = idObjekta;
    }
}
