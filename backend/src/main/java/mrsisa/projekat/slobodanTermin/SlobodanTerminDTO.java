package mrsisa.projekat.slobodanTermin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SlobodanTerminDTO {

    private Long id;
    private String pocetakTermina;
    private String krajTermina;
    private double cijenaTermina;

    public SlobodanTerminDTO(){

    }

    public SlobodanTerminDTO(SlobodanTermin slobodantermin){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.id = slobodantermin.getId();
        this.pocetakTermina = dtf.format(slobodantermin.getPocetakTermina());
        this.krajTermina = dtf.format(slobodantermin.getKrajTermina());
        this.cijenaTermina = slobodantermin.getCijenaTermina();
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
