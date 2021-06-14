package mrsisa.projekat.administratorApoteke;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorApotekeRepository extends JpaRepository<AdministratorApoteke, String> {
    AdministratorApoteke findByUsername(String username);
}
