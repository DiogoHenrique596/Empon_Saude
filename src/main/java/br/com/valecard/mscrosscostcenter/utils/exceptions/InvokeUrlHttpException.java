/**
 * 
 */
package br.com.valecard.mscrosscostcenter.utils.exceptions;

public class InvokeUrlHttpException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6582786207806586171L;

    /**
     * InvokeUrlHttp Exception com String
     * 
     * @param str
     */
    public InvokeUrlHttpException(String str) {
	super(str);
    }

    /**
     * InvokeUrlHttp Exception com throwable
     * 
     * @param throwable
     */
    public InvokeUrlHttpException(Throwable throwable) {
	super(throwable);
    }

    /**
     * InvokeUrlHttp Exception com String e Throwable
     * 
     * @param message
     * @param cause
     */
    public InvokeUrlHttpException(String message, Throwable cause) {
	super(message, cause);
    }

}