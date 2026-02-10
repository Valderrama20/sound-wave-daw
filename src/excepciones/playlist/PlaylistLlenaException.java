package excepciones.playlist;

/**
 * Excepción lanzada cuando se alcanza el límite de canciones permitidas en una playlist.
 */
public class PlaylistLlenaException extends Exception {

    public PlaylistLlenaException() {
    }

    public PlaylistLlenaException(String mensaje) {
        super(mensaje);
    }
}
