package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Cancion;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representa a un creador de contenido de podcasts.
 */
public class Creador {

    // Atributos
    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios = new ArrayList<>();
    private int suscriptores = 0;
    private String descripcion;
    private HashMap<String, String> redesSociales = new HashMap<>();
    private ArrayList<CategoriaPodcast> categoriasPrincipales = new ArrayList<>();

    private static final int MAX_EPISODIOS = 500;

    /**
     * Constructor completo para un creador.
     * @param nombreCanal Nombre del canal de podcast.
     * @param nombre Nombre real del creador.
     * @param descripcion Descripción del canal.
     */
    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor simplificado.
     * @param nombreCanal Nombre del canal.
     * @param nombre Nombre del creador.
     */
    public Creador(String nombreCanal, String nombre) {
        this(nombreCanal, nombre, null);
    }

    // Getters And Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Podcast> getEpisodios() {
        return new ArrayList<>(episodios);
    }

    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HashMap<String, String> getRedesSociales() {
        return new HashMap<>(redesSociales);
    }

    public ArrayList<CategoriaPodcast> getCategoriasPrincipales() {
        return new ArrayList<>(categoriasPrincipales);
    }

    public int getNumEpisodios() {
        return episodios.size();
    }

    // Metodos
    /**
     * Publica un nuevo episodio de podcast en el canal.
     * @param episodio Episodio a publicar.
     * @throws LimiteEpisodiosException Si se ha alcanzado el límite de episodios.
     */
    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if(episodios.size() >= MAX_EPISODIOS) throw new LimiteEpisodiosException();

        episodios.add(episodio);
    }

    /**
     * Genera un reporte de estadísticas del creador.
     * @return Objeto con las estadísticas calculadas.
     */
    public EstadisticasCreador obtenerEstadisticas() {
        return new EstadisticasCreador(this);
    }

    /**
     * Añade o actualiza una red social del creador.
     * @param red Nombre de la red social (ej: "twitter").
     * @param usuario Nombre de usuario en dicha red.
     */
    public void agregarRedSocial(String red, String usuario) {
        redesSociales.put(red.toLowerCase(), usuario);
    }

    /**
     * Calcula el promedio de reproducciones por episodio.
     * @return Promedio de reproducciones.
     */
    public double calcularPromedioReproducciones() {
        return (double) getTotalReproducciones() / episodios.size();
    }

    /**
     * Elimina un episodio basado en su ID.
     * @param idEpisodio ID del episodio a borrar.
     * @throws EpisodioNoEncontradoException Si no se encuentra el episodio.
     */
    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException {

        for(Podcast episodio: episodios) {
            if(episodio.getId().equals(idEpisodio)) {
                episodios.remove(episodio);
                return;
            }
        }

        throw new EpisodioNoEncontradoException();
    }

    /**
     * Calcula el total de reproducciones acumuladas en todos los episodios.
     * @return Total de reproducciones.
     */
    public int getTotalReproducciones() {
        int totalReproduccionesEpisodios = 0;

        for(Podcast episodio : episodios) {
            totalReproduccionesEpisodios+= episodio.getReproducciones();
        }

        return totalReproduccionesEpisodios;
    }

    /**
     * Incrementa en uno el contador de suscriptores.
     */
    public void incrementarSuscriptores(){
        suscriptores++;
    }

    /**
     * Obtiene los episodios más populares del creador.
     * @param cantidad Número de episodios a recuperar.
     * @return Lista de episodios ordenados por reproducciones.
     */
    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad) {
        return episodios
                .stream()
                .sorted(Comparator.comparing(Podcast::getReproducciones).reversed())
                .limit(cantidad)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Obtiene el número de la última temporada publicada.
     * @return Número de temporada.
     */
    public int getUltimaTemporada() {
        return episodios.getLast().getTemporada();
    }

    @Override
    public String toString() {
        return "Creador{" +
                "id='" + id + '\'' +
                ", nombreCanal='" + nombreCanal + '\'' +
                ", nombre='" + nombre + '\'' +
                ", episodios=" + episodios +
                ", suscriptores=" + suscriptores +
                ", descripcion='" + descripcion + '\'' +
                ", redesSociales=" + redesSociales +
                ", categoriasPrincipales=" + categoriasPrincipales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Creador creador = (Creador) o;
        return suscriptores == creador.suscriptores && Objects.equals(id, creador.id) && Objects.equals(nombreCanal, creador.nombreCanal) && Objects.equals(nombre, creador.nombre) && Objects.equals(episodios, creador.episodios) && Objects.equals(descripcion, creador.descripcion) && Objects.equals(redesSociales, creador.redesSociales) && Objects.equals(categoriasPrincipales, creador.categoriasPrincipales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCanal, nombre, episodios, suscriptores, descripcion, redesSociales, categoriasPrincipales);
    }
}
