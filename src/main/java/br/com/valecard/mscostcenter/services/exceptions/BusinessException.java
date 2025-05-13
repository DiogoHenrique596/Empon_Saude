package br.com.valecard.mscostcenter.services.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException( String message ) {
        super( message );
    }
}
