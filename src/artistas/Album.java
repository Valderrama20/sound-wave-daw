package artistas;

import contenido.Cancion;
import enums.GeneroMusical;

import java.util.ArrayList;
import java.util.Date;

public class Album {

    // Atributos
    private String id;
    private String titulo;
    private Artista artista;
    private Date fechaLanzamiento;
    private ArrayList<Cancion> canciones;
    private String portadaURL;
    private String discografica;
    private String tipoAlbum;
    private Plataforma plataforma;

    // Constructor
    public Album(String id, String titulo, Artista artista, Date fechaLanzamiento, String portadaURL, String discografica, String tipoAlbum, Plataforma plataforma) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.portadaURL = portadaURL;
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
        this.plataforma = plataforma;
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
        return canciones;
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

    // Metodos
    public Cancion crearCancion(String titulo, int duracion, GeneroMusical genero) {
        Cancion cancion = new Cancion(
                titulo,
                duracion,
                new Date(),
                plataforma,
                this.artista,
                this,
                genero
        );
    }
}
