package co.edu.unicauca.gesrotesbackend.exceptions;

/**
 *  Clase de excepción personalizada para manejar errores de validación en la aplicación.
*/
public class ValidacionException extends RuntimeException {

    /**
     *  Construye una nueva ValidationException con el mensaje de error proporcionado.
     *  @param mensaje : mensaje de error.
    */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }

    /**
     *  Construye una nueva excepción de validación (ValidationException) con el 
     *  mensaje de error y la causa dada.
     *  @param mensaje : mensaje de error.
     *  @param causa : causa del error.
    */
    public ValidacionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
