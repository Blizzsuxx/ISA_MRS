package mrsisa.projekat.erecept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EreceptRepository extends JpaRepository<Erecept, Integer> {
}
