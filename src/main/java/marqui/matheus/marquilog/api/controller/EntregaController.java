package marqui.matheus.marquilog.api.controller;

import lombok.AllArgsConstructor;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.repository.EntregaRepository;
import marqui.matheus.marquilog.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
@AllArgsConstructor
public class EntregaController {
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody @Valid Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
