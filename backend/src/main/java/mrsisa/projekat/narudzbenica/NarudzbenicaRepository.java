package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long> {
}
