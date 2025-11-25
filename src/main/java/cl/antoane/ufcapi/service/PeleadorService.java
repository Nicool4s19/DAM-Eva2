package cl.antoane.ufcapi.service;

import cl.antoane.ufcapi.model.Peleador;
import cl.antoane.ufcapi.repository.PeleadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeleadorService {

    private final PeleadorRepository repository;

    public PeleadorService(PeleadorRepository repository) {
        this.repository = repository;
    }

    public List<Peleador> obtenerTop5() {
        return repository.findTop5ByOrderByRankingAsc();
    }
}
