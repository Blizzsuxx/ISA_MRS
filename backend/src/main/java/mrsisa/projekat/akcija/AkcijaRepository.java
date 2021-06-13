package mrsisa.projekat.akcija;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkcijaRepository extends JpaRepository<Akcija, Long> {
}
