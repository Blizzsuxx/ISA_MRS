package mrsisa.projekat.slobodanTermin;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.radnik.Radnik;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SlobodanTerminDTO {

    private Long id;
    private String pocetakTermina;
    private String krajTermina;
    private double cijenaTermina;
    private ApotekaDTO apoteka;
    private String imeRadnika;
    private String prezimeRadnika;
    private int ocenaRadnika;


    public SlobodanTerminDTO(){

    }

    public SlobodanTerminDTO(SlobodanTermin slobodantermin){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.id = slobodantermin.getId();
        this.pocetakTermina = slobodantermin.getPocetakTermina().format(dtf);
        this.krajTermina = slobodantermin.getKrajTermina().format(dtf);
        this.cijenaTermina = slobodantermin.getCijenaTermina();

    }

    public SlobodanTerminDTO(SlobodanTermin slobodantermin, Dermatolog r, Apoteka a) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.id = slobodantermin.getId();
        this.pocetakTermina = slobodantermin.getPocetakTermina().format(dtf);
        this.krajTermina = slobodantermin.getKrajTermina().format(dtf);
        this.cijenaTermina = slobodantermin.getCijenaTermina();
        this.apoteka=new ApotekaDTO(a,1);
        this.imeRadnika=r.getFirstName();
        this.prezimeRadnika=r.getLastName();
        this.ocenaRadnika=0;
    }

    public ApotekaDTO getApoteka() {
        return apoteka;
    }

    public void setApoteka(ApotekaDTO apoteka) {
        this.apoteka = apoteka;
    }

    public String getImeRadnika() {
        return imeRadnika;
    }

    public void setImeRadnika(String imeRadnika) {
        this.imeRadnika = imeRadnika;
    }

    public String getPrezimeRadnika() {
        return prezimeRadnika;
    }

    public void setPrezimeRadnika(String prezimeRadnika) {
        this.prezimeRadnika = prezimeRadnika;
    }

    public int getOcenaRadnika() {
        return ocenaRadnika;
    }

    public void setOcenaRadnika(int ocenaRadnika) {
        this.ocenaRadnika = ocenaRadnika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPocetakTermina() {
        return pocetakTermina;
    }

    public void setPocetakTermina(String pocetakTermina) {
        this.pocetakTermina = pocetakTermina;
    }

    public String getKrajTermina() {
        return krajTermina;
    }

    public void setKrajTermina(String krajTermina) {
        this.krajTermina = krajTermina;
    }

    public double getCijenaTermina() {
        return cijenaTermina;
    }

    public void setCijenaTermina(double cijenaTermina) {
        this.cijenaTermina = cijenaTermina;
    }
}
