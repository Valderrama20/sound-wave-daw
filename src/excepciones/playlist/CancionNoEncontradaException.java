package excepciones.playlist;

/**
 * Excepción lanzada cuando no se localiza una canción específica dentro de una lista o álbum.
 */
public class CancionNoEncontradaException extends Exception {

    public CancionNoEncontradaException() {
    }

    public CancionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
