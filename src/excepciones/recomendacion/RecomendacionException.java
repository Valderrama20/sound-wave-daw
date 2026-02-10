package excepciones.recomendacion;

/**
 * Excepción base para errores relacionados con el sistema de recomendaciones.
 */
public class RecomendacionException extends Exception {

    public RecomendacionException() {
    }

    public RecomendacionException(String mensaje) {
        super(mensaje);
    }
}
