package mrsisa.projekat.zalba;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZalbaRepository extends JpaRepository<Zalba, Integer> {
    List<Zalba> findAllByPacijent(String pacijent);

}
