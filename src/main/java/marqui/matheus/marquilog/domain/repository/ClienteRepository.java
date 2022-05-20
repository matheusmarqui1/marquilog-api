package marqui.matheus.marquilog.domain.repository;

import marqui.matheus.marquilog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeIgnoreCase(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Optional<Cliente> findByEmail(String email);
}
