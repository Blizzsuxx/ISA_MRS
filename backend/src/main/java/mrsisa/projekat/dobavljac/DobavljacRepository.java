package mrsisa.projekat.dobavljac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DobavljacRepository extends JpaRepository<Dobavljac, Long> {
}
