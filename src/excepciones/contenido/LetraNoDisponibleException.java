package excepciones.contenido;

/**
 * Excepción lanzada cuando se solicitan las letras de una canción y no están disponibles.
 */
public class LetraNoDisponibleException extends Exception {

    public LetraNoDisponibleException() {
    }

    public LetraNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
