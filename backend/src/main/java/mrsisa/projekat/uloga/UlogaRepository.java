package mrsisa.projekat.uloga;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UlogaRepository extends JpaRepository<Uloga, Integer> {
    List<Uloga> findByName(String name);
}
