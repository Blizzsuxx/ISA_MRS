package mrsisa.projekat.radnoVrijeme;


import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadnoVrijemeRepository extends JpaRepository<RadnoVrijeme,Long> {
}
