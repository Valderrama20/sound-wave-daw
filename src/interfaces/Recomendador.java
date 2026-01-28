package interfaces;

import contenido.Contenido;
import usuarios.Usuario;

import java.util.ArrayList;

public interface Recomendador {
    ArrayList<Contenido> recomendar(Usuario usuario);
    ArrayList<Contenido> obtenerSimilares(Contenido contenido);
}
