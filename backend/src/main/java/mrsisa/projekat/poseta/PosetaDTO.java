package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;

import java.util.List;

public class PosetaDTO {


    public PosetaDTO(Long id, Pacijent pacijent, Radnik radnik, String pocetak, String kraj, Apoteka apoteka, List<Erecept> erecepti) {
        this.id = id;
        this.pacijent = pacijent;
        this.radnik = radnik;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.apoteka = apoteka;
        this.erecepti = erecepti;
    }

    private Long id;

    private Pacijent pacijent;

    private Radnik radnik;

    private String pocetak;

    private String kraj;

    private List<Erecept> erecepti;

    private Apoteka apoteka;

    public PosetaDTO() {

    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public List<Erecept> getErecepti() {
        return erecepti;
    }

    public void setErecepti(List<Erecept> erecepti) {
        this.erecepti = erecepti;
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }



    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }




}
