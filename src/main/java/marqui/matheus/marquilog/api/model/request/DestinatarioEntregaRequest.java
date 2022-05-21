package marqui.matheus.marquilog.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DestinatarioEntregaRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String complemento;
}
