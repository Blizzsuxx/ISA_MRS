package mrsisa.projekat.zalba;

public class ZalbaDTO {
    private String id;
    private String naslov;
    private String pacijent;
    private String tipZalbe;
    private String datumVrijeme;
    private String idObjekta;
    private String text;

    public ZalbaDTO() {}

    public ZalbaDTO(String id, String naslov, String pacijent, String tipZalbe, String datumVrijeme, String idObjekta) {
        this.id = id;
        this.naslov = naslov;
        this.pacijent = pacijent;
        this.tipZalbe = tipZalbe;
        this.datumVrijeme = datumVrijeme;
        this.idObjekta = idObjekta;
    }

    public ZalbaDTO(Zalba z){
        this.id = z.getId().toString();
        this.naslov = z.getNaslov();
        this.pacijent = z.getPacijent();
        this.tipZalbe = z.getTipZalbe();
        this.datumVrijeme = z.getDatumVrijeme();
        this.idObjekta = z.getIdObjekta();
        this.text = z.getText();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }

    public String getTipZalbe() {
        return tipZalbe;
    }

    public void setTipZalbe(String tipZalbe) {
        this.tipZalbe = tipZalbe;
    }

    public String getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(String datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public String getIdObjekta() {
        return idObjekta;
    }

    public void setIdObjekta(String idObjekta) {
        this.idObjekta = idObjekta;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
