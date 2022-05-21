package marqui.matheus.marquilog.api.model;

import lombok.*;
import marqui.matheus.marquilog.domain.model.StatusEntrega;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntregaResponse {

    private Long Id;
    private String nomeCliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
