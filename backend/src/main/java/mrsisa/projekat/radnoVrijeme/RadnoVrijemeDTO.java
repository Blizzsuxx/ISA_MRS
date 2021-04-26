package mrsisa.projekat.radnoVrijeme;

import java.time.LocalTime;

public class RadnoVrijemeDTO {
    private Integer dermatolog;
    private Long apoteka;
    private LocalTime pocetakRadnogVremena;
    private LocalTime krajRadnogVremena;


    public RadnoVrijemeDTO(RadnoVrijeme radnoVrijeme){
        this.dermatolog = radnoVrijeme.getDermatolog().getId();
        this.apoteka = radnoVrijeme.getApoteka().getId();
        this.pocetakRadnogVremena = radnoVrijeme.getPocetakRadnogVremena();
        this.krajRadnogVremena = radnoVrijeme.getKrajRadnogVremena();

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

    public LocalTime getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public LocalTime getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }
}
