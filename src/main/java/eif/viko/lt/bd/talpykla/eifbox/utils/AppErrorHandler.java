package eif.viko.lt.bd.talpykla.eifbox.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppErrorHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ProblemDetail handleInvalidValueError(RuntimeException ex) {

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", LocalDateTime.now());
        pd.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return pd;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ProblemDetail handleFieldValidationError(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setDetail("Validation errors");
        pd.setProperty("timestamp", LocalDateTime.now());
        pd.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        pd.setProperty("errors", errors);
        return pd;
    }


}
