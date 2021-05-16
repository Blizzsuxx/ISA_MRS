package mrsisa.projekat.ponuda;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda,Long> {
}
