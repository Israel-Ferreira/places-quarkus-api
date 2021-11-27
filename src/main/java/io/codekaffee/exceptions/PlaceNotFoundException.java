package io.codekaffee.exceptions;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException() {
        super("Erro: Lugar não encontrado na base de dados");
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(Throwable cause) {
        super(cause);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
