package mrsisa.projekat.apoteka;

public class ApotekaDTO {
    private String ime;
    private String mjesto;
    private String ptt;
    private String ulica;
    private String broj;

    public ApotekaDTO() {

    }
    public ApotekaDTO(Apoteka apoteka){
        this.ime = apoteka.getIme();
        this.mjesto = apoteka.getAdresa().getMesto();
        this.ptt = apoteka.getAdresa().getPtt();
        this.ulica  = apoteka.getAdresa().getUlica();
        this.broj = apoteka.getAdresa().getBroj();
    }

    public ApotekaDTO(String naziv, String mjesto, String ptt, String ulica, String broj) {
        this.ime = naziv;
        this.mjesto = mjesto;
        this.ptt = ptt;
        this.ulica = ulica;
        this.broj = broj;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }
}
