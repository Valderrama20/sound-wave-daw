package interfaces;

import modelo.contenido.Contenido;
import usuarios.Usuario;

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
     */
    ArrayList<Contenido> recomendar(Usuario usuario);

    /**
     * Obtiene contenido similar a un contenido dado
     *
     * @param contenido
     * @return una lista de contenido
     */
    ArrayList<Contenido> obtenerSimilares(Contenido contenido);
}
