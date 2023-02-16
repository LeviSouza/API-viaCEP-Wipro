package wipro.latam.consultacep.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorsAdvice {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        String mensagem = ex.getMessage();

        return ResponseEntity.status(status).body("CEP não encontrado!");
    }

}
