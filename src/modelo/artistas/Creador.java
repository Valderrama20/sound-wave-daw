package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Cancion;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.*;

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

    // Constructor
    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

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
    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if(episodios.size() >= MAX_EPISODIOS) throw new LimiteEpisodiosException();

        episodios.add(episodio);
    }

    public EstadisticasCreador obtenerEstadisticas() {
        return new EstadisticasCreador(this);
    }

    public void agregarRedSocial(String red, String usuario) {
        redesSociales.put(red, usuario);
    }

    public double calcularPromedioReproducciones() {
        return (double) getTotalReproducciones() / episodios.size();
    }

    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException {

        for(Podcast episodio: episodios) {
            if(episodio.getId().equals(idEpisodio)) {
                episodios.remove(episodio);
                return;
            }
        }

        throw new EpisodioNoEncontradoException();
    }

    public int getTotalReproducciones() {
        int totalReproduccionesEpisodios = 0;

        for(Podcast episodio : episodios) {
            totalReproduccionesEpisodios+= episodio.getReproducciones();
        }

        return totalReproduccionesEpisodios;
    }

    public void incrementarSuscriptores(){
        suscriptores++;
    }

    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad) {
        // Hacemos una copia paran no mutar el original
        ArrayList<Podcast> copiaEpisodios = new ArrayList<>(episodios);

        // Ordenamos de manera descendiente
        copiaEpisodios.sort(Comparator.comparing(Podcast::getReproducciones).reversed());

        // Retornamos un la lista recortada
        return (ArrayList<Podcast>) copiaEpisodios.subList(0, Math.min(cantidad, copiaEpisodios.size()));
    }

    public int getUltimaTemporada() {
        return episodios.size();
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
