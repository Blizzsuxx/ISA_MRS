package mrsisa.projekat.zalba;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdgovorRepository  extends JpaRepository<Odgovor, Long> {
    List<Odgovor> findAllByZalba(Zalba z);
}
