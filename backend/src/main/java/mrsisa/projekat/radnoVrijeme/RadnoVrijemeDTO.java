package mrsisa.projekat.radnoVrijeme;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RadnoVrijemeDTO {
    private Integer radnik;
    private Long apoteka;
    private String pocetakRadnogVremena;
    private String krajRadnogVremena;


    public RadnoVrijemeDTO(RadnoVrijeme radnoVrijeme){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        if(radnoVrijeme.getDermatolog()!=null)
            this.radnik = radnoVrijeme.getDermatolog().getId();
        else
            this.radnik  = radnoVrijeme.getFarmaceuet().getId();
        this.pocetakRadnogVremena = dtf.format(radnoVrijeme.getPocetakRadnogVremena());
        this.krajRadnogVremena = dtf.format(radnoVrijeme.getKrajRadnogVremena());

    }
    public RadnoVrijemeDTO() {
    }

    public Integer getRadnik() {
        return radnik;
    }

    public void setRadnik(Integer radnik) {
        this.radnik = radnik;
    }

    public Long getApoteka() {
        return apoteka;
    }

    public void setApoteka(Long apoteka) {
        this.apoteka = apoteka;
    }

    public String getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(String pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public String getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(String krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }
}
