package mrsisa.projekat.pacijent;

public class TempDTO {
    private String id;
    private String identifikator;
    private String tipZalbe;

    public TempDTO() {}

    public TempDTO(String id, String identifikator){
        this.id = id;
        this.identifikator = identifikator;
    }

    public TempDTO(String id, String identifikator, String tipZalbe) {
        this.id = id;
        this.identifikator = identifikator;
        this.tipZalbe = tipZalbe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifikator() {
        return identifikator;
    }

    public void setIdentifikator(String identifikator) {
        this.identifikator = identifikator;
    }

    public String getTipZalbe() {
        return tipZalbe;
    }

    public void setTipZalbe(String tipZalbe) {
        this.tipZalbe = tipZalbe;
    }
}
