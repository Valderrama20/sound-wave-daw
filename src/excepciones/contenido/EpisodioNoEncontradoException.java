package excepciones.contenido;

/**
 * Excepción lanzada cuando no se encuentra un episodio específico dentro de un podcast.
 */
public class EpisodioNoEncontradoException extends Exception {

    public EpisodioNoEncontradoException() {
    }

    public EpisodioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
