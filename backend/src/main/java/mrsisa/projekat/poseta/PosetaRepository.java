package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosetaRepository extends JpaRepository<Poseta, Long> {
    @Query(value = "SELECT * FROM poseta WHERE radnik_korisnik = ?1 AND otkazano = null", nativeQuery = true)
    public List<Poseta> findByRadnikAktivno(Integer id);
}
