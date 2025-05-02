package br.com.valecard.mscostcenter.services.exceptions;

public class GenericServiceException extends Exception {

    private static final long serialVersionUID = -6712327642247331955L;
    private Class sourceException;

    public GenericServiceException( String str ) {
        super( str );
    }

    public GenericServiceException( Throwable throwable ) {
        super( throwable );
    }

    public GenericServiceException( String message, Throwable cause ) {
        super( message, cause );
    }

    public GenericServiceException( String message, Class sourceException ) {
        super( message );
        this.sourceException = sourceException;
    }

    public Class getSourceException() {
        return sourceException;
    }
}
