package excepciones.contenido;

/**
 * Excepción lanzada cuando la transcripción de un podcast no está disponible.
 */
public class TranscripcionNoDisponibleException extends Exception {

    public TranscripcionNoDisponibleException() {
    }

    public TranscripcionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
