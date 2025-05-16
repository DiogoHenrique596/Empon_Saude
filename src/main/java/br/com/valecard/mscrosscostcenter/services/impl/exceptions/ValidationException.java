package br.com.valecard.mscrosscostcenter.services.impl.exceptions;

import java.util.List;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.dtos.ProblemObject;
import lombok.Getter;

@Getter
public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    private List<ProblemObject> problemObjectList;
    private Class sourceException;

    public ValidationException( String str ) {
        super( str );
    }

    public ValidationException( Throwable throwable ) {
        super( throwable );
    }

    public ValidationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ValidationException( String message, Throwable cause, List<ProblemObject> problemObjectList ) {
        super( message, cause );
        this.problemObjectList = problemObjectList;
    }

    public ValidationException( String message, Class sourceException ) {
        super( message );
        this.sourceException = sourceException;
    }

    public ValidationException( String message, List<ProblemObject> problemObjectList ) {
        super( message );
        this.problemObjectList = problemObjectList;
    }


}
