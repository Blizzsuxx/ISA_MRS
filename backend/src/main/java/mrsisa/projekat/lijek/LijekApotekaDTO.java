package mrsisa.projekat.lijek;

public class LijekApotekaDTO {
    private String nazivApoteke;
    private double cijena;

    public LijekApotekaDTO() {}

    public LijekApotekaDTO(String nazivApoteke, double cijena) {
        this.nazivApoteke = nazivApoteke;
        this.cijena = cijena;
    }

    public String getNazivApoteke() {
        return nazivApoteke;
    }

    public void setNazivApoteke(String nazivApoteke) {
        this.nazivApoteke = nazivApoteke;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
}
