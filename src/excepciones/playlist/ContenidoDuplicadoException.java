package excepciones.playlist;

/**
 * Excepción lanzada cuando se intenta agregar contenido que ya existe en la playlist.
 */
public class ContenidoDuplicadoException extends Exception {

    public ContenidoDuplicadoException() {
    }

    public ContenidoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
