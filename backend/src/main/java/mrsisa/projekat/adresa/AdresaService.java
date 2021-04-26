package mrsisa.projekat.adresa;



import java.util.List;

public class AdresaService {
    private final AdresaRepository adresaRepository;

    public AdresaService (AdresaRepository adr){  this.adresaRepository=adr;}
    public Adresa save(Adresa a){ return this.adresaRepository.save(a); }

    public List<Adresa> findAll(){
        return adresaRepository.findAll();
    }
    //TODO ako korisnik ima adresu, zasto on da ima apoteke?? pogledati klasu adresa

}
