package marqui.matheus.marquilog.domain.service;

import lombok.RequiredArgsConstructor;
import marqui.matheus.marquilog.domain.exception.EntidadeNaoEncontradaException;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BuscaEntregaService {

    private final EntregaRepository entregaRepository;

    public Entrega buscarPorId(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }
}
