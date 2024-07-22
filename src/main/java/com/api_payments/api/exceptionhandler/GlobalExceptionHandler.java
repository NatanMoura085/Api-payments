package com.api_payments.api.exceptionhandler;

import com.api_payments.domain.exceptionhandler.EntidadeNaoEncontrada;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Campos-Invalidos");
        problemDetail.setType(URI.create("http://localhost:8081/v1/api/campos-invalidos"));
        Map<String,String> fields = ex.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(objectError->((FieldError) objectError).getField(),objectError->messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        problemDetail.setProperty("fields",fields);
        return handleExceptionInternal(ex, problemDetail,headers, status, request);
    }
    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ProblemDetail entidadeNaoEncontrada(EntidadeNaoEncontrada e){
        ProblemDetail problemDetail1 = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail1.setTitle(e.getMessage());
        problemDetail1.setType(URI.create("http://localhost:8081/v1/api/entidade-nao-encontrada"));
        return problemDetail1;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleIntegrityViolation(DataIntegrityViolationException e){
        ProblemDetail problemDetail2 = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail2.setTitle("recurso ja em uso!");
        problemDetail2.setType(URI.create("http://localhost/v1/api/recuro-esta-em-uso"));
        return problemDetail2;
    }
}
