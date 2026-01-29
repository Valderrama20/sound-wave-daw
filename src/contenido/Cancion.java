package contenido;

import enums.GeneroMusical;
import interfaces.Descargable;
import interfaces.Reproducible;
import usuarios.Usuario;

import java.util.Date;

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
     * @param id Identificador único
     * @param titulo Título de la canción
     * @param reproducciones Número de reproducciones
     * @param likes Número de likes
     * @param duracionSegundos Duración en segundos
     * @param disponible Indica si está disponible
     * @param fechaPublicacion Fecha de publicación
     * @param usuario Usuario que la subió
     * @param plataforma Plataforma de publicación
     * @param letra Letra completa
     * @param artista Artista principal
     * @param album Álbum
     * @param genero Género musical
     * @param audioURL URL del audio
     * @param explicit Contenido explícito
     * @param isrc Código ISRC
     */
    public Cancion(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion, Usuario usuario, Plataforma plataforma, String letra, Artista artista, Album album, GeneroMusical genero, String audioURL, boolean explicit, String isrc) {
        super(id, titulo, reproducciones, likes, duracionSegundos, disponible, fechaPublicacion, usuario, plataforma);
        this.letra = letra;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.audioURL = audioURL;
        this.explicit = explicit;
        ISRC = isrc;
    }

    /**
     * Constructor para crear una canción sin artista.
     */
    public Cancion(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion, Usuario usuario, Plataforma plataforma, String letra, Album album, GeneroMusical genero, String audioURL, boolean explicit, String isrc) {
        this(
                id,
                titulo,
                reproducciones,
                likes,
                duracionSegundos,
                disponible,
                fechaPublicacion,
                usuario,
                plataforma,
                letra,
                null,
                album,
                genero,
                audioURL,
                explicit,
                isrc
        );
    }

    /**
     * Constructor para crear una canción sin álbum.
     */
    public Cancion(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion, Usuario usuario, Plataforma plataforma, String letra, Artista artista, GeneroMusical genero, String audioURL, boolean explicit, String isrc) {
        this(
                id,
                titulo,
                reproducciones,
                likes,
                duracionSegundos,
                disponible,
                fechaPublicacion,
                usuario,
                plataforma,
                letra,
                artista,
                null,
                genero,
                audioURL,
                explicit,
                isrc
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
