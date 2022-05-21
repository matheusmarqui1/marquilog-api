package marqui.matheus.marquilog.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaRequest {
    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioEntregaRequest destinatario;

    @NotNull
    private BigDecimal taxa;
}
