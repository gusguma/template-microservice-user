package es.gusguma.template.microservice.user.exceptions;

public class BadGatewayException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Gateway Exception (502)";

    public BadGatewayException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
