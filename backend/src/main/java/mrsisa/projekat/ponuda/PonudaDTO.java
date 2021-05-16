package mrsisa.projekat.ponuda;

public class PonudaDTO {
    private Long id;
    private String dobavljac;
    private String nazivPonude;
    private double cijenaPonude;
    private int status;
    public PonudaDTO(){

    }

    public PonudaDTO(Ponuda ponuda){
        this.id = ponuda.getId();
        this.dobavljac = ponuda.getDobavljac().getUsername();
        this.nazivPonude = ponuda.getNazivPonude();
        this.cijenaPonude  = ponuda.getCijenaPonude();
        this.status = ponuda.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(String dobavljac) {
        this.dobavljac = dobavljac;
    }

    public String getNazivPonude() {
        return nazivPonude;
    }

    public void setNazivPonude(String nazivPonude) {
        this.nazivPonude = nazivPonude;
    }

    public double getCijenaPonude() {
        return cijenaPonude;
    }

    public void setCijenaPonude(double cijenaPonude) {
        this.cijenaPonude = cijenaPonude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}