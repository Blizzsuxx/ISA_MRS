package mrsisa.projekat.istorijaPromjena;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IstorijaPromjenaRepository  extends JpaRepository<IstorijaPromjena,Long> {
}
