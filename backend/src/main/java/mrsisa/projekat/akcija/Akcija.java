package mrsisa.projekat.akcija;

import javax.persistence.*;

import mrsisa.projekat.lijek.Lijek;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Akcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "procenatPopusta", nullable = false)
    private int procenatPopusta;
    @Column(name = "opis", nullable = true)
    private String opis;
    @Column(name = "datumOd",nullable = false)
    private LocalDateTime datumOd;
    @Column (name="datumDo",nullable = false)
    private LocalDateTime datumDo;

    public Akcija(int procenatPopusta, LocalDateTime datumOd, LocalDateTime datumDo){
        this.procenatPopusta = procenatPopusta;
        this.datumDo = datumDo;
        this.datumOd = datumOd;
    }

    public Akcija(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(int procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Akcija(int procenatPopusta, String opis) {
        super();
        this.procenatPopusta = procenatPopusta;

        this.opis = opis;
    }

    public LocalDateTime getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDateTime datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDateTime getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDateTime datumDo) {
        this.datumDo = datumDo;
    }
}
