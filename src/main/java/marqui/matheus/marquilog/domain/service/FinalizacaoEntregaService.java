package marqui.matheus.marquilog.domain.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import marqui.matheus.marquilog.domain.exception.RegraNegocioException;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.model.StatusEntrega;
import marqui.matheus.marquilog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private final BuscaEntregaService buscaEntregaService;
    private final EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntregaService.buscarPorId(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}
