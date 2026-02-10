package excepciones.recomendacion;

/**
 * Excepción lanzada cuando el modelo de recomendación no ha sido entrenado o inicializado.
 */
public class ModeloNoEntrenadoException extends RecomendacionException {

    public ModeloNoEntrenadoException() {
    }

    public ModeloNoEntrenadoException(String mensaje) {
        super(mensaje);
    }
}
