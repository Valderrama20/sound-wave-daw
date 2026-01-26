package interfaces;

import java.util.ArrayList;

public interface Recomendador {
    ArrayList<Contenido> recomendar(Usuario usuario);
    ArrayList<Contenido> obtenerSimilares(Contenido contenido);
}
