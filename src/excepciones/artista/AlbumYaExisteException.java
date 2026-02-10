package excepciones.artista;

/**
 * Excepción lanzada cuando se intenta crear un álbum con un título que ya existe para el artista.
 */
public class AlbumYaExisteException extends Exception {

    public AlbumYaExisteException() {
    }

    public AlbumYaExisteException(String mensaje) {
        super(mensaje);
    }
}
