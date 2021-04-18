package mrsisa.projekat.slobodanTermin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlobodanTerminRepository extends JpaRepository<SlobodanTermin,Long> {
}
