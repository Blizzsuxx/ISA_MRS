package mrsisa.projekat.dermatolog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DermatologRepository extends JpaRepository<Dermatolog, Long> {
}
