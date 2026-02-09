package excepciones.usuario;

/**
 * Excepción lanzada cuando un usuario gratuito debe ver un anuncio antes de continuar.
 */
public class AnuncioRequeridoException extends Exception{

    public AnuncioRequeridoException() {
    }

    public AnuncioRequeridoException(String message) {
        super(message);
    }
}
