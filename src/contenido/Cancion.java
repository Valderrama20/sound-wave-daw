package contenido;

import artistas.Album;
import enums.GeneroMusical;
import interfaces.Descargable;
import interfaces.Reproducible;
import java.util.Date;
import java.util.UUID;
import artistas.Artista;

/**
 * Representa una canción dentro de la plataforma.
 * Contiene información sobre su contenido, artista, álbum y reproducción.
 */
public class Cancion extends Contenido implements Reproducible, Descargable {

    // Atributos
    private String letra;               // Letra completa de la canción
    private Artista artista;            // Referencia al artista (agregación)
    private Album album;                // Referencia al álbum (agregación)
    private GeneroMusical genero;       // Género de la canción
    private String audioURL;            // URL del archivo de audio
    private boolean explicit;           // Si contiene contenido explícito
    private String ISRC;                // Código internacional de grabación

    /**
     * Constructor principal de la clase Cancion.
     *
     * @param titulo Título de la canción
     * @param duracionSegundos Duración en segundos
     * @param fechaPublicacion Fecha de publicación
     * @param plataforma Plataforma de publicación
     * @param artista Artista principal
     * @param album Álbum
     * @param genero Género musical
     */
    public Cancion(String titulo, int duracionSegundos, Date fechaPublicacion, Plataforma plataforma, Artista artista, Album album, GeneroMusical genero) {
        super(UUID.randomUUID().toString(), titulo, 0, 0, duracionSegundos, true, fechaPublicacion, plataforma);
        this.letra = "";
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.audioURL = "";
        this.explicit = false;
        ISRC = "";
    }

    /**
     * Constructor para crear una canción sin artista.
     */
    public Cancion(String titulo, int duracionSegundos, Date fechaPublicacion, Plataforma plataforma, Album album, GeneroMusical genero) {
        this(
                titulo,
                duracionSegundos,
                fechaPublicacion,
                plataforma,
                null,
                album,
                genero
        );
    }

    /**
     * Constructor para crear una canción sin álbum.
     */
    public Cancion(String titulo, int duracionSegundos, Date fechaPublicacion, Plataforma plataforma, Artista artista, GeneroMusical genero) {
        this(
                titulo,
                duracionSegundos,
                fechaPublicacion,
                plataforma,
                artista,
                null,
                genero
        );
    }

    // Getters and setters
    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    // Metodos
    @Override
    public void reproducir() {
       // TODO
    }

    @Override
    public boolean descargable() {
        // TODO
        return false;
    }

    @Override
    public boolean eliminarDescarga() {
        // TODO
        return false;
    }

    @Override
    public int espacioRequerido() {
        // TODO
        return 0;
    }

    @Override
    public void play() {
        // TODO
    }

    @Override
    public void pause() {
        // TODO
    }

    @Override
    public void stop() {
        // TODO
    }

    @Override
    public int getDuracion() {
        return super.getDuracionSegundos();
    }

    /**
     * Devuelve la letra de la canción.
     */
    public  String obtenerLetra() {
        return letra;
    }

    /**
     * Indica si la canción es explícita.
     */
    public boolean esExplicit(){
        return explicit;
    }

    /**
     * Cambia el género musical de la canción.
     *
     * @param nuevoGenero Nuevo género
     */
    public void cambiarGenero(GeneroMusical nuevoGenero){
        this.setGenero(genero);
    }
}
