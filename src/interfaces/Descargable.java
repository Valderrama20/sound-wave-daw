package interfaces;

import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;

/**
 * Contrato para contenido descargable para uso offline
 */
public interface Descargable {

    /**
     * Descarga el contenido. Debe fallar si supera límites o ya está descargado
     *
     * @return true si la descarga se realizó correctamente, false en caso contrario
     */
    boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException;

    /**
     * Elimina la descarga existente
     *
     * @return true si la descarga fue eliminada correctamente, false si no existía o falló
     */
    boolean eliminarDescarga();

    /**
     * Devuelve el espacio (aproximado) requerido para almacenar la descarga (en MB)
     *
     * @return espacio requerido para la descarga en MB
     */
    int espacioRequerido();
}
