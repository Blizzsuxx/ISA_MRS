package mrsisa.projekat.godisnjiodmor;

import java.time.format.DateTimeFormatter;

public class GodisnjiOdmorDTO {
    private Long id;
    private String datumOd;
    private String datumDo;
    private boolean odobren;
    private String korisnicko;

    public GodisnjiOdmorDTO(){

    }

    public GodisnjiOdmorDTO(Long id, String datumOd, String datumDo, boolean odobren) {
        this.id = id;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.odobren = odobren;
    }


    public GodisnjiOdmorDTO(GodisnjiOdmor godisnjiOdmor) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.id = godisnjiOdmor.getId();
        this.datumOd = godisnjiOdmor.getDatumOd().format(dtf);
        this.datumDo = godisnjiOdmor.getDatumDo().format(dtf);
        this.odobren = godisnjiOdmor.getOdobren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public String getKorisnicko() {
        return korisnicko;
    }

    public void setKorisnicko(String korisnicko) {
        this.korisnicko = korisnicko;
    }
}
