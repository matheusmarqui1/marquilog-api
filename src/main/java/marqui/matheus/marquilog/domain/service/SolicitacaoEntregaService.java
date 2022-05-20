package marqui.matheus.marquilog.domain.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import marqui.matheus.marquilog.domain.exception.RegraNegocioException;
import marqui.matheus.marquilog.domain.model.Cliente;
import marqui.matheus.marquilog.domain.model.Entrega;
import marqui.matheus.marquilog.domain.model.StatusEntrega;
import marqui.matheus.marquilog.domain.repository.ClienteRepository;
import marqui.matheus.marquilog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;

    private final CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService
                .buscarPorId(entrega.getCliente().getId())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
