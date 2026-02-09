package excepciones.contenido;

/**
 * Excepción lanzada cuando no se encuentra el archivo de audio asociado al contenido.
 */
public class ArchivoAudioNoEncontradoException extends Exception {

    public ArchivoAudioNoEncontradoException() {
    }

    public ArchivoAudioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
