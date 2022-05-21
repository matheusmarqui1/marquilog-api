package marqui.matheus.marquilog.api.controller;

import lombok.RequiredArgsConstructor;
import marqui.matheus.marquilog.api.assembler.Assembler;
import marqui.matheus.marquilog.api.model.OcorrenciaResponse;
import marqui.matheus.marquilog.api.model.request.OcorrenciaRequest;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.model.Ocorrencia;
import marqui.matheus.marquilog.domain.service.BuscaEntregaService;
import marqui.matheus.marquilog.domain.service.RegistroOcorrenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private final RegistroOcorrenciaService registroOcorrenciaService;
    private final BuscaEntregaService buscaEntregaService;
    private final Assembler<OcorrenciaResponse> assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrar(@PathVariable Long entregaId,
                                        @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
        Ocorrencia ocorrenciaRegistrada =
                registroOcorrenciaService.registrar(entregaId, ocorrenciaRequest.getDescricao());

        return assembler.convert(ocorrenciaRegistrada, OcorrenciaResponse.class);
    }

    @GetMapping
    public List<OcorrenciaResponse> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscarPorId(entregaId);

        return assembler.toCollectionModel(entrega.getOcorrencias(), OcorrenciaResponse.class);
    }
}
