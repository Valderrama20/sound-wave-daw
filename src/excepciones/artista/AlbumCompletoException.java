package excepciones.artista;

/**
 * Excepción lanzada cuando se intenta agregar contenido a un álbum que ha alcanzado su capacidad máxima.
 */
public class AlbumCompletoException extends Exception {

    public AlbumCompletoException() {
    }

    public AlbumCompletoException(String mensaje) {
        super(mensaje);
    }
}
