package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacijentRepository extends JpaRepository<Pacijent, String> {
    Pacijent findByUsername(String username);
}
