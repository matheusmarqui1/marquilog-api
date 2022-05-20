package marqui.matheus.marquilog.domain.service;

import lombok.AllArgsConstructor;
import marqui.matheus.marquilog.domain.exception.RegraNegocioException;
import marqui.matheus.marquilog.domain.model.Cliente;
import marqui.matheus.marquilog.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogoClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream().anyMatch(c -> !c.equals(cliente));
        if(emailEmUso) throw new RegraNegocioException("O e-mail já está sendo usado por outro cliente.");
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
