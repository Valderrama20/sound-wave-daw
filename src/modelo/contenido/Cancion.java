package modelo.contenido;

import modelo.artistas.Album;
import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;

import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

import modelo.artistas.Artista;

/**
 * Representa una canción dentro de la plataforma.
 * Contiene información sobre su contenido, artista, álbum y reproducción.
 */
public class Cancion extends Contenido implements Reproducible, Descargable {

    // Atributos
    private String letra = null;                        // Letra completa de la canción
    private Artista artista;                          // Referencia al artista (agregación)
    private Album album;                              // Referencia al álbum (agregación)
    private GeneroMusical genero;                     // Género de la canción
    private String audioURL = null;                     // URL del archivo de audio
    private boolean explicit = false;                 // Si contiene contenido explícito
    private String ISRC;                         // Código internacional de grabación
    private boolean reproduciendo = false;
    private boolean pausado = false;
    private boolean descargado = false;

    /**
     * Constructor principal de la clase Cancion.
     *
     * @param titulo Título de la canción
     * @param duracionSegundos Duración en segundos
     * @param artista Artista principal
     * @param genero Género musical
     * @param letra Letra de la cancion
     * @param explicit Es contenido explícito
     */

    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit ) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;
        this.ISRC = generarISRC();

    }
    /**
     * Constructor para crear una canción sin letra y contenido explícito.
     */
    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        this(titulo, duracionSegundos, artista, genero, null, false);

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

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public boolean isPausado() {
        return pausado;
    }

    public boolean isDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    // Metodos
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
       if(!disponible) throw new ContenidoNoDisponibleException();

       aumentarReproducciones();
       play();
    }

    @Override
    public void play() {
        reproduciendo = true;
        System.out.println("Mostrando informacion...");
    }

    @Override
    public void pause() {
        if(reproduciendo) pausado = true;
    }

    @Override
    public void stop() {
        reproduciendo = false;
        pausado = false;
    }

    @Override
    public int getDuracion() {
        return getDuracionSegundos();
    }

    @Override
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (descargado) throw new ContenidoYaDescargadoException();
        return true;
    }

    @Override
    public boolean eliminarDescarga() {
        descargado = false;
        return true;
    }

    @Override
    public int espacioRequerido() {
        // TODO
        return 0;
    }

    public String generarISRC() {
        String year = String.valueOf(Year.now().getValue()).substring(2);

        int number = new AtomicInteger(1).getAndIncrement();

        String formattedNumber = String.format("%05d", number);

        return "ES" + "ABC" + year + formattedNumber;
    }

    /**
     * Devuelve la letra de la canción.
     */
    public  String obtenerLetra() throws LetraNoDisponibleException {
        if(letra == null) throw new LetraNoDisponibleException();

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

    public void validarAudioURL() throws ArchivoAudioNoEncontradoException {
         if(audioURL == null) throw new ArchivoAudioNoEncontradoException();
    }

    @Override
    public String toString() {
        return "Cancion{" +
                ", artista=" + artista +
                ", duracion=" + duracionSegundos +
                '}';
    }
}
