package excepciones.artista;

/**
 * Excepción lanzada cuando un artista no verificado intenta realizar acciones restringidas.
 */
public class ArtistaNoVerificadoException extends Exception {

    public ArtistaNoVerificadoException() {
    }

    public ArtistaNoVerificadoException(String mensaje) {
        super(mensaje);
    }
}
