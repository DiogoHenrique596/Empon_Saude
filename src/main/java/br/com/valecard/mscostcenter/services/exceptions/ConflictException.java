package br.com.valecard.mscostcenter.services.exceptions;

public class ConflictException extends BusinessException {
    public ConflictException( String message) {
        super(message);
    }
}
