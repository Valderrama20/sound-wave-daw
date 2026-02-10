package excepciones.plataforma;

/**
 * Excepción general lanzada cuando no se localiza un contenido (canción, podcast, etc.).
 */
public class ContenidoNoEncontradoException extends Exception {

    public ContenidoNoEncontradoException() {
    }

    public ContenidoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
