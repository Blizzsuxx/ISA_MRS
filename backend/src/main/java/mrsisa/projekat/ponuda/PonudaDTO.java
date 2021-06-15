package mrsisa.projekat.ponuda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PonudaDTO {
    private Long id;
    private String dobavljac;
    private String nazivPonude;
    private double cijenaPonude;
    private int status;
    private Long idNarudzbenice;
    private String rokPonude;
    private String rokNarudzbenice;
    private boolean statusEdita;

    public PonudaDTO(){

    }

    public PonudaDTO(Ponuda ponuda){
        this.id = ponuda.getId();
        this.dobavljac = ponuda.getDobavljac().getUsername();
        this.nazivPonude = ponuda.getNazivPonude();
        this.cijenaPonude  = ponuda.getCijenaPonude();
        this.status = ponuda.getStatus();
        this.rokPonude = ponuda.getRokPonude();
        this.rokNarudzbenice = ponuda.getNarudzbenica().getRok().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.statusEdita = LocalDate.now().isBefore(LocalDate.parse(this.rokNarudzbenice, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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

    public Long getIdNarudzbenice() {
        return idNarudzbenice;
    }

    public void setIdNarudzbenice(Long idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }

    public String getRokPonude() {
        return rokPonude;
    }

    public void setRokPonude(String rokPonude) {
        this.rokPonude = rokPonude;
    }

    public String getRokNarudzbenice() {
        return rokNarudzbenice;
    }

    public void setRokNarudzbenice(String rokNarudzbenice) {
        this.rokNarudzbenice = rokNarudzbenice;
    }

    public boolean isStatusEdita() {
        return statusEdita;
    }

    public void setStatusEdita(boolean statusEdita) {
        this.statusEdita = statusEdita;
    }
}
