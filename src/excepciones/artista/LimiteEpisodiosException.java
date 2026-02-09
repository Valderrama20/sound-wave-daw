package excepciones.artista;

/**
 * Excepción lanzada cuando se supera el límite de episodios permitidos para un podcast.
 */
public class LimiteEpisodiosException extends Exception {

    public LimiteEpisodiosException() {
    }

    public LimiteEpisodiosException(String mensaje) {
        super(mensaje);
    }
}
