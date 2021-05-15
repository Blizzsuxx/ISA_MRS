package mrsisa.projekat.narudzbenica;

import java.time.format.DateTimeFormatter;

public class NarudzbenicaDTO {
    private Long id;
    private int brojPonuda;
    private String rok;
    private boolean zavrsena;


    public NarudzbenicaDTO(){

    }

    public NarudzbenicaDTO(Narudzbenica narudzbenica){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.id = narudzbenica.getId();
        this.rok = narudzbenica.getRok().format(dtf);
        this.zavrsena  = narudzbenica.isZavrsena();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojPonuda() {
        return brojPonuda;
    }

    public void setBrojPonuda(int brojPonuda) {
        this.brojPonuda = brojPonuda;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public boolean isZavrsena() {
        return zavrsena;
    }

    public void setZavrsena(boolean zavrsena) {
        this.zavrsena = zavrsena;
    }
}
