package excepciones.recomendacion;

/**
 * Excepción lanzada cuando no hay suficiente historial de usuario para generar recomendaciones.
 */
public class HistorialVacioException extends RecomendacionException {

    public HistorialVacioException() {
    }

    public HistorialVacioException(String mensaje) {
        super(mensaje);
    }
}
