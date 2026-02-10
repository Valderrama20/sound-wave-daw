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
     * Construye una canción con todos sus atributos.
     * @param titulo Título de la canción.
     * @param duracionSegundos Duración en segundos.
     * @param artista Artista intérprete.
     * @param genero Género musical.
     * @param letra Letra completa de la canción.
     * @param explicit Indicador de contenido explícito.
     * @throws DuracionInvalidaException Si la duración no es válida.
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
     * Crea una canción con configuración estándar (sin letra, no explícita).
     * @param titulo Título de la canción.
     * @param duracionSegundos Duración.
     * @param artista Artista.
     * @param genero Género.
     * @throws DuracionInvalidaException Si la duración es inválida.
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
    /**
     * Inicia la reproducción de la canción si está disponible.
     * @throws ContenidoNoDisponibleException Si la canción no está disponible.
     */
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
       if(!disponible) throw new ContenidoNoDisponibleException();

       aumentarReproducciones();
       play();
    }

    /**
     * Reanuda o inicia la reproducción.
     */
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

    /**
     * Marca la canción como descargada.
     * @return true si se descargó correctamente.
     * @throws LimiteDescargasException Si se superan los límites de descarga.
     * @throws ContenidoYaDescargadoException Si ya estaba descargada.
     */
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

    /**
     * Calcula el espacio aproximado requerido de almacenamiento.
     */
    @Override
    public int espacioRequerido() {
        return 0;
    }

    /**
     * Genera un código ISRC único para la canción.
     * @return Código ISRC generado.
     */
    public String generarISRC() {
        String year = String.valueOf(Year.now().getValue()).substring(2);

        int number = new AtomicInteger(1).getAndIncrement();

        String formattedNumber = String.format("%05d", number);

        return "ES" + "ABC" + year + formattedNumber;
    }

    /**
     * Obtiene la letra de la canción si existe.
     * @return Texto de la letra.
     * @throws LetraNoDisponibleException Si no hay letra asociada.
     */
    public  String obtenerLetra() throws LetraNoDisponibleException {
        if(letra == null || letra.isEmpty()) throw new LetraNoDisponibleException();

        return letra;
    }

    /**
     * Verifica si el contenido es explícito.
     * @return true si es explícito.
     */
    public boolean esExplicit(){
        return explicit;
    }

    /**
     * Actualiza el género musical de la canción.
     * @param nuevoGenero Nuevo género a establecer.
     */
    public void cambiarGenero(GeneroMusical nuevoGenero){
        this.setGenero(genero);
    }

    /**
     * Verifica que la URL del archivo de audio sea válida.
     * @throws ArchivoAudioNoEncontradoException Si la URL es nula o vacía.
     */
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
