package marqui.matheus.marquilog.api.exceptionhandler;

import marqui.matheus.marquilog.domain.exception.EntidadeNaoEncontradaException;
import marqui.matheus.marquilog.domain.exception.RegraNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problema.Campo> campos = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> new Problema.Campo(((FieldError) objectError).getField(), objectError.getDefaultMessage()))
                .collect(Collectors.toList());

        return handleExceptionInternal(
                ex, Problema.builder()
                        .status(status.value())
                        .dataHora(OffsetDateTime.now())
                        .erro("Um ou mais campos apresentam erros de validação. Cheque-os e tente novamente...")
                        .campos(campos)
                        .build(),
                headers, status, request);
    }

    @ExceptionHandler({RegraNegocioException.class})
    public ResponseEntity<Object> handleRegraNegocio(RegraNegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(
                ex,
                Problema.builder().status(status.value()).dataHora(OffsetDateTime.now()).erro(ex.getMessage()).build(),
                new HttpHeaders(),
                status,
                request
        );
    }

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<Object> handleRegraNegocio(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(
                ex,
                Problema.builder().status(status.value()).dataHora(OffsetDateTime.now()).erro(ex.getMessage()).build(),
                new HttpHeaders(),
                status,
                request
        );
    }
}
