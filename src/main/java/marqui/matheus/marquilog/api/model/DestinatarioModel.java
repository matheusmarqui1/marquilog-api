package marqui.matheus.marquilog.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioModel {
    private String nomeDestinatario;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
