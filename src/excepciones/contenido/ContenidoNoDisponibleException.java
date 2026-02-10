package excepciones.contenido;

/**
 * Excepción lanzada cuando se intenta acceder a un contenido que no está disponible (inactivo, bloqueado por región, etc.).
 */
public class ContenidoNoDisponibleException extends Exception {

    public ContenidoNoDisponibleException() {
    }

    public ContenidoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
