package modelo.artistas;

import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;
import enums.GeneroMusical;

import java.util.*;

/**
 * Representa un álbum musical publicado por un artista, que contiene una colección de canciones.
 */
public class Album {

    // Atributos
    private String id;
    private String titulo;
    private Artista artista;
    private Date fechaLanzamiento;
    private ArrayList<Cancion> canciones;
    private String portadaURL = null;
    private String discografica;
    private String tipoAlbum;

    private static final int MAX_CANCIONES = 20;

    /**
     * Crea una instancia de Album con información completa.
     * @param titulo Título del álbum.
     * @param artista Artista propietario.
     * @param fechaLanzamiento Fecha de lanzamiento.
     * @param discografica Sello discográfico.
     * @param tipoAlbum Tipo de lanzamiento (LP, EP, Single).
     */
    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }

    /**
     * Constructor simplificado para albums.
     * @param titulo Título del álbum.
     * @param artista Artista creador del álbum.
     * @param fechaLanzamiento Fecha de publicación.
     */
    public Album(String titulo, Artista artista, Date fechaLanzamiento) {
        this(titulo, artista, fechaLanzamiento, null, null);
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public ArrayList<Cancion> getCanciones() {
        return new ArrayList<>(canciones);
    }

    public void addCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    public int getMaxCanciones() {
        return MAX_CANCIONES;
    }

    // Metodos
    /**
     * Crea y añade una nueva canción al álbum.
     * @param titulo Título de la canción.
     * @param duracionSegundos Duración en segundos.
     * @param genero Género musical.
     * @param letra Letra de la canción.
     * @param explicit Indica si tiene contenido explícito.
     * @return La canción creada.
     * @throws AlbumCompletoException Si el álbum ya alcanzó el máximo de canciones.
     * @throws DuracionInvalidaException Si la duración es inválida.
     */
    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero, String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException {
        if(MAX_CANCIONES <= canciones.size()) throw new AlbumCompletoException();

        Cancion cancion = new Cancion(titulo, duracionSegundos, artista, genero, letra, explicit);
        cancion.setAlbum(this);

        addCancion(cancion);
        artista.addCancion(cancion);

        return cancion;
    }

    /**
     * Crea y añade una canción con información básica.
     * @param titulo Título de la canción.
     * @param duracionSegundos Duración.
     * @param genero Género musical.
     * @return La canción creada.
     * @throws AlbumCompletoException Si el álbum está lleno.
     * @throws DuracionInvalidaException Si la duración no es válida.
     */
    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero) throws AlbumCompletoException, DuracionInvalidaException {
        return crearCancion(titulo, duracionSegundos, genero, null, false);
    }
    
    /**
     * Elimina una canción del álbum por su posición.
     * @param posicion Posición en la lista (1-based).
     * @throws CancionNoEncontradaException Si la posición no es válida.
     */
    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {

        if(canciones.size() <= posicion || posicion <= 0 ) throw new CancionNoEncontradaException();

        canciones.remove(posicion);
    }

    /**
     * Elimina una canción específica del álbum.
     * @param cancion Objeto Cancion a eliminar.
     * @throws CancionNoEncontradaException Si la canción no está en el álbum.
     */
    public void eliminarCancion(Cancion cancion) throws CancionNoEncontradaException {
        if(!canciones.remove(cancion)) throw new CancionNoEncontradaException();
    }

    /**
     * Calcula la duración total del álbum sumando la duración de todas sus canciones.
     * @return Duración total en segundos.
     */
    public int getDuracionTotal() {
        int duracionTotalAlbum = 0;

        for (Cancion cancion : canciones){
            duracionTotalAlbum += cancion.getDuracion();
        }

        return duracionTotalAlbum;
    }

    /**
     * Devuelve la duración total formateada en horas:minutos:segundos.
     * @return String con la duración formateada.
     */
    public String getDuracionTotalFormateada() {
        int duracionTotal = getDuracionTotal();

        return duracionTotal / 3600 + ":" + duracionTotal / 60 + ":" + duracionTotal % 60;
    }

    /**
     * Obtiene el número de canciones del álbum.
     * @return Cantidad de canciones.
     */
    public int getNumCanciones() {
        return canciones.size();
    }

    /**
     * Ordena las canciones del álbum por popularidad (reproducciones) de mayor a menor.
     */
    public void ordenarPorPopularidad() {
        canciones.sort(Comparator.comparing(Cancion::getReproducciones).reversed());
    }

    /**
     * Obtiene una canción específica dada su posición.
     * @param posicion Posición en la lista de canciones (1-based).
     * @return La canción encontrada.
     * @throws CancionNoEncontradaException Si la posición está fuera de rango.
     */
    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if(canciones.size() <= posicion) throw new CancionNoEncontradaException();

        // Por alguna razon tengo que poner menos uno para obtener la primera cancion
        // el tes me pìde la posicion 1, pero el array list comienza en 0, por eso rompe
        // el test
        return canciones.get(posicion-1);
    }

    /**
     * Calcula el total de reproducciones acumuladas de todas las canciones del álbum.
     * @return Total de reproducciones.
     */
    public int getTotalReproducciones() {
        int reproduccionesTotalAlbum = 0;

        for (Cancion cancion : canciones){
            reproduccionesTotalAlbum += cancion.getReproducciones();
        }

        return reproduccionesTotalAlbum;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artista=" + artista +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", canciones=" + canciones +
                ", portadaURL='" + portadaURL + '\'' +
                ", discografica='" + discografica + '\'' +
                ", tipoAlbum='" + tipoAlbum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(titulo, album.titulo) && Objects.equals(artista, album.artista) && Objects.equals(fechaLanzamiento, album.fechaLanzamiento) && Objects.equals(canciones, album.canciones) && Objects.equals(portadaURL, album.portadaURL) && Objects.equals(discografica, album.discografica) && Objects.equals(tipoAlbum, album.tipoAlbum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, artista, fechaLanzamiento, canciones, portadaURL, discografica, tipoAlbum);
    }
}
