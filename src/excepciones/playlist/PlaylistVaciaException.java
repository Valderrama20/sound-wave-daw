package excepciones.playlist;

/**
 * Excepción lanzada cuando se intenta reproducir una playlist sin contenido.
 */
public class PlaylistVaciaException extends Exception {

    public PlaylistVaciaException() {
    }

    public PlaylistVaciaException(String mensaje) {
        super(mensaje);
    }
}
