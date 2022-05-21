package marqui.matheus.marquilog.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaRequest {
    @Valid
    @NotNull
    private ClienteIdRequest cliente;

    @Valid
    @NotNull
    private DestinatarioEntregaRequest destinatario;

    @NotNull
    private BigDecimal taxa;
}
