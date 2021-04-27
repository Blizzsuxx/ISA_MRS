package mrsisa.projekat.radnoVrijeme;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RadnoVrijemeDTO {
    private Integer dermatolog;
    private Long apoteka;
    private String pocetakRadnogVremena;
    private String krajRadnogVremena;


    public RadnoVrijemeDTO(RadnoVrijeme radnoVrijeme){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.dermatolog = radnoVrijeme.getDermatolog().getId();
        this.apoteka = radnoVrijeme.getApoteka().getId();
        this.pocetakRadnogVremena = dtf.format(radnoVrijeme.getPocetakRadnogVremena());
        this.krajRadnogVremena = dtf.format(radnoVrijeme.getKrajRadnogVremena());

    }
    public RadnoVrijemeDTO() {
    }

    public Integer getDermatolog() {
        return dermatolog;
    }

    public void setDermatolog(Integer dermatolog) {
        this.dermatolog = dermatolog;
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
