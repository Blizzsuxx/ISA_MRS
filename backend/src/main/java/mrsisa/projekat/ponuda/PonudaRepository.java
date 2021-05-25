package mrsisa.projekat.ponuda;


import mrsisa.projekat.dobavljac.Dobavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda,Long> {
    List<Ponuda> findAllByDobavljac(Dobavljac d);
}
