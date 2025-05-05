package br.com.valecard.mscostcenter.services.exceptions.handler;

import java.time.OffsetDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static br.com.valecard.mscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_INTERNAL_ERROR;
import static br.com.valecard.mscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;

import br.com.valecard.mscostcenter.services.exceptions.BusinessException;
import br.com.valecard.mscostcenter.services.exceptions.ConflictException;
import br.com.valecard.mscostcenter.services.exceptions.ResourceNotFoundException;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;
import br.com.valecard.mscostcenter.services.exceptions.dtos.ProblemHttp;
import br.com.valecard.mscostcenter.services.exceptions.enums.ProblemHttpType;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger LOG = LogManager.getLogger( ApiExceptionHandler.class );

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRuntimeException( RuntimeException runtimeException,
                                                                WebRequest webRequest ) {

        ProblemHttp exceptionResponse = new ProblemHttp( HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ProblemHttpType.SYSTEM_ERROR.toString(), ProblemHttpType.SYSTEM_ERROR.getTitle(),
                EXCEPTION_INTERNAL_ERROR, runtimeException.getMessage(), getUrlRequest( webRequest ),
                OffsetDateTime.now(), null );

        LOG.error( runtimeException.getMessage(), runtimeException );

        return super.handleExceptionInternal( runtimeException, exceptionResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest );
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException( BusinessException businessException,
                                                                 WebRequest webRequest ) {

        ProblemHttp exceptionResponse = new ProblemHttp( HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ProblemHttpType.SYSTEM_ERROR.getUri(), ProblemHttpType.SYSTEM_ERROR.getTitle(),
                EXCEPTION_INTERNAL_ERROR, EXCEPTION_INTERNAL_ERROR, getUrlRequest( webRequest ),
                OffsetDateTime.now(), null );


        LOG.error( businessException.getMessage(), businessException );

        return super.handleExceptionInternal( businessException, exceptionResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest );
    }

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<Object> handleConflictException( ConflictException conflictException,
                                                                 WebRequest webRequest ) {

        ProblemHttp exceptionResponse = new ProblemHttp( HttpStatus.CONFLICT.value(),
                ProblemHttpType.CONFLICT.getUri(), ProblemHttpType.CONFLICT.getTitle(),
                null, conflictException.getMessage(), getUrlRequest( webRequest ),
                OffsetDateTime.now(), null );


        LOG.error( conflictException.getMessage(), conflictException );

        return super.handleExceptionInternal( conflictException, exceptionResponse, new HttpHeaders(),
                HttpStatus.CONFLICT, webRequest );
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleValidationException( ValidationException validationException,
                                                                   WebRequest webRequest ) {

        ProblemHttp exceptionResponse = new ProblemHttp( HttpStatus.BAD_REQUEST.value(),
                ProblemHttpType.BAD_REQUEST.getUri(), ProblemHttpType.BAD_REQUEST.getTitle(),
                EXCEPTION_INVALID_DATA, validationException.getMessage(), getUrlRequest( webRequest ),
                OffsetDateTime.now(), validationException.getProblemObjectList() );


        LOG.warn( validationException.getMessage() );

        return super.handleExceptionInternal( validationException, exceptionResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleCostCenterNotFoundException(
            ResourceNotFoundException costCenterNotFoundException,
            WebRequest webRequest ) {

        ProblemHttp exceptionResponse = new ProblemHttp( HttpStatus.NOT_FOUND.value(),
                ProblemHttpType.RESOURCE_NOT_FOUND.getUri(), ProblemHttpType.RESOURCE_NOT_FOUND.getTitle(),
                ProblemHttpType.RESOURCE_NOT_FOUND.getTitle(), costCenterNotFoundException.getMessage(), getUrlRequest( webRequest ),
                OffsetDateTime.now(), null );


        LOG.error( costCenterNotFoundException.getMessage(), costCenterNotFoundException );

        return super.handleExceptionInternal( costCenterNotFoundException, exceptionResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest );
    }

    private String getUrlRequest( WebRequest webRequest ) {
        String url = "URL unavailable";
        if ( webRequest instanceof ServletWebRequest servletWebRequest ) {
            url = servletWebRequest.getRequest().getRequestURL().toString();
        }
        return url;
    }

}
