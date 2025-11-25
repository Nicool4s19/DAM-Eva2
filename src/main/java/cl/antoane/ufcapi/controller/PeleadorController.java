package cl.antoane.ufcapi.controller;

import cl.antoane.ufcapi.model.Peleador;
import cl.antoane.ufcapi.service.PeleadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peleadores")
@CrossOrigin("*")
public class PeleadorController {

    private final PeleadorService service;

    public PeleadorController(PeleadorService service) {
        this.service = service;
    }

    @GetMapping("/top5")
    public List<Peleador> top5() {
        return service.obtenerTop5();
    }
}
