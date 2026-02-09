package excepciones.plataforma;

/**
 * Excepción lanzada cuando no se encuentra un artista en la plataforma.
 */
public class ArtistaNoEncontradoException extends Exception {

    public ArtistaNoEncontradoException() {
    }

    public ArtistaNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
