package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosetaRepository extends JpaRepository<Poseta, Long> {
}
