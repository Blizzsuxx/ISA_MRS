package mrsisa.projekat.lijek;

import mrsisa.projekat.dobavljac.Dobavljac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LijekRepository extends JpaRepository<Lijek, Long> {
    Lijek findByNaziv(String naziv);
    void deleteByNaziv(String naziv);
    List<Lijek> findAllByDobavljac(Dobavljac d);
}
