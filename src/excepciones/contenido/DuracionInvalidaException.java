package excepciones.contenido;

/**
 * Excepción lanzada cuando se asigna una duración no válida (negativa o cero) a un contenido.
 */
public class DuracionInvalidaException extends Exception {

    public DuracionInvalidaException() {
    }

    public DuracionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
