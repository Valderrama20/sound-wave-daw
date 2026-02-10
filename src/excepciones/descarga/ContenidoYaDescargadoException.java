package excepciones.descarga;

/**
 * Excepción lanzada cuando se intenta descargar un contenido que ya se encuentra descargado.
 */
public class ContenidoYaDescargadoException extends Exception {

    public ContenidoYaDescargadoException() {
    }

    public ContenidoYaDescargadoException(String mensaje) {
        super(mensaje);
    }
}
