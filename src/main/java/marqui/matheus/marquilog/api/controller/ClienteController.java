package marqui.matheus.marquilog.api.controller;

import lombok.AllArgsConstructor;
import marqui.matheus.marquilog.domain.model.Cliente;
import marqui.matheus.marquilog.domain.repository.ClienteRepository;
import marqui.matheus.marquilog.domain.service.CatalogoClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

	private final CatalogoClienteService catalogoClienteService;
	private final ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return catalogoClienteService.listarTodos();
	}

	@GetMapping("{clienteId}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long clienteId) {
		return catalogoClienteService.buscarPorId(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) {
		return cliente != null ?
				ResponseEntity.status(HttpStatus.CREATED)
						.body(catalogoClienteService.salvar(cliente)) : ResponseEntity.badRequest().build();
	}

	@PutMapping("{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
											 @RequestBody @Valid Cliente cliente) {
		return clienteRepository.findById(clienteId)
				.map(clienteSalvo -> {
					cliente.setId(clienteSalvo.getId());
					return ResponseEntity.ok(catalogoClienteService.salvar(cliente));
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();

		catalogoClienteService.deletar(clienteId);

		return ResponseEntity.noContent().build();
	}

}
