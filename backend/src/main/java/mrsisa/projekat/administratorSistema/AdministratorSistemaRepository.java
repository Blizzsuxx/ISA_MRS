package mrsisa.projekat.administratorSistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorSistemaRepository extends JpaRepository<AdministratorSistema, Long> {
}
