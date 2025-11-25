package cl.antoane.ufcapi.repository;

import cl.antoane.ufcapi.model.Peleador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeleadorRepository extends JpaRepository<Peleador, Integer> {

    List<Peleador> findTop5ByOrderByRankingAsc();
}
