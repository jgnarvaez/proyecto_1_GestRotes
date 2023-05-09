package co.edu.unicauca.gesrotesbackend.exceptions;

public class HTTPException extends RuntimeException {
    private final int statusCode;
    private final String message;

    public HTTPException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
