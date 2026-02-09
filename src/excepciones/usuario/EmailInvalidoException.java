package excepciones.usuario;

/**
 * Excepción lanzada cuando el formato del email proporcionado no es válido.
 */
public class EmailInvalidoException extends Exception{

    public EmailInvalidoException() {
    }

    public EmailInvalidoException(String message) {
        super(message);
    }
}
