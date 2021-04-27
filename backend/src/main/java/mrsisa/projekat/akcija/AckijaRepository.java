package mrsisa.projekat.akcija;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AckijaRepository extends JpaRepository<Akcija, Long> {
}
