package mrsisa.projekat.apoteka;

public class ApotekaDTO {
    private String naziv;
    private String mjesto;
    private String ptt;
    private String ulica;
    private int broj;

    public ApotekaDTO() {

    }

    public ApotekaDTO(String naziv, String mjesto, String ptt, String ulica, int broj) {
        this.naziv = naziv;
        this.mjesto = mjesto;
        this.ptt = ptt;
        this.ulica = ulica;
        this.broj = broj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }
}
