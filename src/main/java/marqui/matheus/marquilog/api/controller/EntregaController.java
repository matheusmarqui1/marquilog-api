package marqui.matheus.marquilog.api.controller;

import lombok.AllArgsConstructor;
import marqui.matheus.marquilog.api.assembler.Assembler;
import marqui.matheus.marquilog.api.model.EntregaResponse;
import marqui.matheus.marquilog.api.model.request.EntregaRequest;
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
    private final Assembler<EntregaResponse> assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse solicitar(@RequestBody @Valid EntregaRequest entregaRequest) {
        return assembler.convert(solicitacaoEntregaService.solicitar(
                assembler.convert(entregaRequest, Entrega.class)
        ), EntregaResponse.class);
    }

    @GetMapping
    public List<EntregaResponse> listar() {
        return assembler.toCollectionModel(entregaRepository.findAll(), EntregaResponse.class);
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaResponse> buscarPorId(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map( entrega -> ResponseEntity.ok(assembler.convert(entrega, EntregaResponse.class)) )
                .orElse(ResponseEntity.notFound().build());
    }
}
