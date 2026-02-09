package excepciones.descarga;

/**
 * Excepción lanzada cuando se alcanza el número máximo de descargas permitido.
 */
public class LimiteDescargasException extends Exception {

    public LimiteDescargasException() {
    }

    public LimiteDescargasException(String mensaje) {
        super(mensaje);
    }
}
