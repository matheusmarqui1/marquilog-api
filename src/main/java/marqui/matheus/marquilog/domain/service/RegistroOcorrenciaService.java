package marqui.matheus.marquilog.domain.service;

import lombok.RequiredArgsConstructor;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.model.Ocorrencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistroOcorrenciaService {
    private final BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscarPorId(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
