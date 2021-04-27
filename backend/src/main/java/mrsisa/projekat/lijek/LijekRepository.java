package mrsisa.projekat.lijek;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LijekRepository extends JpaRepository<Lijek, Long> {
    Lijek findByNaziv(String naziv);
    void deleteByNaziv(String naziv);
}
