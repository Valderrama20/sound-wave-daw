package interfaces;

import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import excepciones.recomendacion.RecomendacionException;

import java.util.ArrayList;

/**
 * Contrato del sistema de recomendaciones
 */
public interface Recomendador {

    /**
     * Genera recomendaciones personalizadas para el usuario
     *
     * @param usuario
     * @return una lista de contenidos
     * @throws RecomendacionException si ocurre un error en la recomendación
     */
    ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException;

    /**
     * Obtiene contenido similar a un contenido dado
     *
     * @param contenido
     * @return una lista de contenido
     * @throws RecomendacionException si ocurre un error al buscar similares
     */
    ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException;
}
