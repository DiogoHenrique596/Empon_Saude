package br.com.valecard.mscrosscostcenter.services.impl.exceptions.enums;


public enum ProblemHttpType {

    BAD_REQUEST( "Bad request.", "/bad-request" ),
    CONFLICT( "Entity in use.", "/conflict" ),
    DOMAIN_ERROR( "Violation of Business Rule.", "/domain-error" ),
    INVALID_DATA( "Invalid data.", "/invalid-data" ),
    INVALID_PARAMETER( "Invalid parameter.", "/invalid-parameter" ),
    RESOURCE_NOT_FOUND( "Resource not found.", "/resource-not-found" ),
    SYSTEM_ERROR( "System error.", "/system-error" ),
    UNAUTHORIZED( "Unauthorized.", "/unauthorized" ),
    WRONG_VALUE( "Wrong value.", "/wrong-value" );

    private final String title;
    private final String uri;

    ProblemHttpType( String title, String uri ) {
        this.title = title;
        this.uri = "/api-doc/ms-cost-center" + uri;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
