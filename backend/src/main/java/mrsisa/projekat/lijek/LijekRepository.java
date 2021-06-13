package mrsisa.projekat.lijek;

import mrsisa.projekat.dobavljac.Dobavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LijekRepository extends JpaRepository<Lijek, Long> {
    Lijek findByNaziv(String naziv);
    void deleteByNaziv(String naziv);
    List<Lijek> findAllByDobavljac(Dobavljac d);


    @Query("select s from Lijek s where s.id = ?1")
    Lijek findOneById(Long prvi);
}
