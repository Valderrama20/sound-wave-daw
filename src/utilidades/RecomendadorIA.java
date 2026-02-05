package utilidades;

import enums.AlgoritmoRecomendacion;
import enums.CategoriaPodcast;
import enums.GeneroMusical;
import interfaces.Recomendador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;
import excepciones.recomendacion.HistorialVacioException;
import excepciones.recomendacion.ModeloNoEntrenadoException;
import excepciones.recomendacion.RecomendacionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class RecomendadorIA implements Recomendador {

    // Atributos
    private HashMap<String, ArrayList<String>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido> catalogoReferencia;

    private static final double UMBRAL_DEFAULT = 0.6;

    public RecomendadorIA() {
        this(AlgoritmoRecomendacion.HIBRIDO);
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
        this.umbralSimilitud = UMBRAL_DEFAULT;
        this.matrizPreferencias = new HashMap<>();
        this.historialCompleto = new HashMap<>();
        this.catalogoReferencia = new ArrayList<>();
        this.modeloEntrenado = false;
    }

    // Implementación de Recomendador
    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        if (!modeloEntrenado) {
            throw new ModeloNoEntrenadoException("El modelo no ha sido entrenado.");
        }
        
        // Verificamos si el usuario tiene historial o me gustas
        if ((usuario.getHistorial() == null || usuario.getHistorial().isEmpty()) && 
            (usuario.getContenidosLiked() == null || usuario.getContenidosLiked().isEmpty())) {
            throw new HistorialVacioException("El usuario no tiene historial suficiente para recomendaciones.");
        }

        // Aseguramos que tenemos las preferencias actualizadas
        actualizarPreferencias(usuario);
        ArrayList<String> preferencias = matrizPreferencias.get(usuario.getId());
        
        if (preferencias == null || preferencias.isEmpty()) {
             throw new HistorialVacioException("No se pudieron determinar preferencias para el usuario.");
        }

        ArrayList<Contenido> recomendaciones = new ArrayList<>();
        
        // Evitar recomendar cosas que ya ha visto o likeado
        Set<String> idsConocidos = new HashSet<>();
        if (usuario.getHistorial() != null) {
            for (Contenido c : usuario.getHistorial()) idsConocidos.add(c.getId());
        }
        if (usuario.getContenidosLiked() != null) {
            for (Contenido c : usuario.getContenidosLiked()) idsConocidos.add(c.getId());
        }

        for (Contenido candidato : catalogoReferencia) {
            if (idsConocidos.contains(candidato.getId())) continue;
            
            double similitud = calcularSimilitudContenido(candidato, preferencias);
            if (similitud >= umbralSimilitud) {
                recomendaciones.add(candidato);
            }
        }

        return recomendaciones;
    }

    @Override
    public ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException {
        ArrayList<Contenido> similares = new ArrayList<>();
        
        // Determinar características del contenido base
        GeneroMusical generoBase = null;
        CategoriaPodcast categoriaBase = null;
        
        if (contenido instanceof Cancion) {
            generoBase = ((Cancion) contenido).getGenero();
        } else if (contenido instanceof Podcast) {
            categoriaBase = ((Podcast) contenido).getCategoria();
        }

        for (Contenido candidato : catalogoReferencia) {
            // No incluir el mismo contenido
            if (candidato.getId().equals(contenido.getId())) continue;

            boolean coincide = false;
            
            if (candidato instanceof Cancion && generoBase != null) {
                if (((Cancion) candidato).getGenero() == generoBase) {
                    coincide = true;
                }
            } else if (candidato instanceof Podcast && categoriaBase != null) {
                if (((Podcast) candidato).getCategoria() == categoriaBase) {
                    coincide = true;
                }
            }
            
            if (coincide) {
                similares.add(candidato);
            }
        }
        
        return similares;
    }

    // Métodos propios
    public void entrenarModelo(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            actualizarPreferencias(usuario);
        }
        this.modeloEntrenado = true;
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
        setCatalogoReferencia(catalogo);
        entrenarModelo(usuarios);
    }

    public double calcularSimilitud(Usuario u1, Usuario u2) {
        ArrayList<String> p1 = matrizPreferencias.get(u1.getId());
        ArrayList<String> p2 = matrizPreferencias.get(u2.getId());

        if (p1 == null || p2 == null || p1.isEmpty() || p2.isEmpty()) return 0.0;

        Set<String> union = new HashSet<>(p1);
        union.addAll(p2);
        
        Set<String> interseccion = new HashSet<>(p1);
        interseccion.retainAll(p2);

        if (union.isEmpty()) return 0.0;
        
        return (double) interseccion.size() / union.size();
    }

    public void actualizarPreferencias(Usuario usuario) {
        ArrayList<String> preferencias = new ArrayList<>();
        ArrayList<Contenido> todoLoVisto = new ArrayList<>(); 
        
        if (usuario.getHistorial() != null) {
            todoLoVisto.addAll(usuario.getHistorial());
        }
        if (usuario.getContenidosLiked() != null) {
            todoLoVisto.addAll(usuario.getContenidosLiked());
        }

        historialCompleto.put(usuario.getId(), todoLoVisto);

        for (Contenido c : todoLoVisto) {
            preferencias.addAll(c.getTags());
            
            if (c instanceof Cancion) {
                GeneroMusical g = ((Cancion) c).getGenero();
                if (g != null) preferencias.add(g.name());
            } else if (c instanceof Podcast) {
                CategoriaPodcast cat = ((Podcast) c).getCategoria();
                if (cat != null) preferencias.add(cat.name());
            }
        }
        
        matrizPreferencias.put(usuario.getId(), preferencias);
    }

    public HashMap<String, Integer> obtenerGenerosPopulares() {
        HashMap<String, Integer> conteo = new HashMap<>();
        
        for (ArrayList<String> prefs : matrizPreferencias.values()) {
            for (String p : prefs) {
                conteo.put(p, conteo.getOrDefault(p, 0) + 1);
            }
        }
        return conteo;
    }

    // Método privado
    private double calcularSimilitudContenido(Contenido contenido, ArrayList<String> preferencias) {
        if (preferencias == null || preferencias.isEmpty()) return 0.0;
        
        double matches = 0;
        Set<String> rasgosContenido = new HashSet<>(contenido.getTags());
        
        if (contenido instanceof Cancion) {
            GeneroMusical g = ((Cancion) contenido).getGenero();
            if (g != null) rasgosContenido.add(g.name());
        } else if (contenido instanceof Podcast) {
            CategoriaPodcast cat = ((Podcast) contenido).getCategoria();
            if (cat != null) rasgosContenido.add(cat.name());
        }

        for (String rasgo : rasgosContenido) {
            for (String pref : preferencias) {
                if (pref.equals(rasgo)) {
                    matches++;
                }
            }
        }

        if (preferencias.size() == 0) return 0.0;
        return matches / preferencias.size();
    }

    // Getters y Setters
    public AlgoritmoRecomendacion getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getUmbralSimilitud() {
        return umbralSimilitud;
    }

    public void setUmbralSimilitud(double umbralSimilitud) {
        this.umbralSimilitud = umbralSimilitud;
    }

    public boolean isModeloEntrenado() {
        return modeloEntrenado;
    }

    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        HashMap<String, ArrayList<String>> copia = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : matrizPreferencias.entrySet()) {
            copia.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copia;
    }

    public void setCatalogoReferencia(ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
    }
}
