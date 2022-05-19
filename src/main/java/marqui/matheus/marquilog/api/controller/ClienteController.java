package marqui.matheus.marquilog.api.controller;

import marqui.matheus.marquilog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@GetMapping
	public List<Cliente> listar() {
		return null;
	}
}
