package interfaces;

import java.util.List;

public interface Recomendador {
    List<Contenido> recomendar(Usuario usuario);
    List<Contenido> obtenerSimilares(Contenido contenido);
}
