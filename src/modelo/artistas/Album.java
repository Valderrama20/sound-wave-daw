package modelo.artistas;

import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;
import enums.GeneroMusical;

import java.util.*;

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

    // Constructor
    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }

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
    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero, String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException {
        if(MAX_CANCIONES >= canciones.size()) throw new AlbumCompletoException();

        Cancion cancion = new Cancion(titulo, duracionSegundos, artista, genero, letra, explicit);
        addCancion(cancion);
        return cancion;
    }

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero) throws AlbumCompletoException, DuracionInvalidaException {
        return crearCancion(titulo, duracionSegundos, genero, null, false);
    }

    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {

        if(canciones.size() <= posicion) throw new CancionNoEncontradaException();

        canciones.remove(posicion);
    }

    public void eliminarCancion(Cancion cancion) throws CancionNoEncontradaException {
        if(!canciones.remove(cancion)) throw new CancionNoEncontradaException();
    }

    public int getDuracionTotal() {
        int duracionTotalAlbum = 0;

        for (Cancion cancion : canciones){
            duracionTotalAlbum += cancion.getDuracion();
        }

        return duracionTotalAlbum;
    }

    public String getDuracionTotalFormateada() {
        int duracionTotal = getDuracionTotal();

        return duracionTotal / 3600 + ":" + duracionTotal / 60 + ":" + duracionTotal % 60;
    }

    public int getNumCanciones() {
        return canciones.size();
    }

    public void ordenarPorPopularidad() {
        canciones.sort(Comparator.comparing(Cancion::getReproducciones));
    }

    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if(canciones.size() <= posicion) throw new CancionNoEncontradaException();

        return canciones.get(posicion);
    }

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
