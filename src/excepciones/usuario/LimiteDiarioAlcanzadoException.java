package excepciones.usuario;

/**
 * Excepción lanzada cuando un usuario alcanza su límite diario de reproducciones o acciones.
 */
public class LimiteDiarioAlcanzadoException extends Exception{

    public LimiteDiarioAlcanzadoException() {
    }

    public LimiteDiarioAlcanzadoException(String message) {
        super(message);
    }
}
