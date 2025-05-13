package br.com.valecard.mscrosscostcenter.services.exceptions;

public class ConflictException extends BusinessException {
    public ConflictException( String message) {
        super(message);
    }
}
