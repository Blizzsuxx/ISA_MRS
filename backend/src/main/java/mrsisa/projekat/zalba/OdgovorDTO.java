package mrsisa.projekat.zalba;

public class OdgovorDTO {
    private int id;
    private String text;
    private String korisnickoIme;
    private String datumVrijeme;

    public OdgovorDTO() {}

    public OdgovorDTO(int id, String text, String korisnickoIme, String datumVrijeme) {
        this.id = id;
        this.text = text;
        this.korisnickoIme = korisnickoIme;
        this.datumVrijeme = datumVrijeme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(String datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }
}
