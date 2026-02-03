package modelo.contenido;

import enums.CategoriaPodcast;
import excepciones.contenido.*;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;

import java.util.ArrayList;

/**
 * Representa un episodio de podcast dentro de la plataforma.
 * Contiene información sobre el creador, temporada, categoría y contenido.
 */
public class Podcast extends Contenido implements Reproducible, Descargable {

    // Atributos
    private Creador creador;                        // Creador del podcast (agregación)
    private int numeroEpisodio;                     // Número de episodio
    private int temporada;                          // Temporada a la que pertenece
    private String descripcion = null;              // Descripción del episodio
    private CategoriaPodcast categoria;             // Categoría
    private ArrayList<String> invitados;            // Lista de invitados
    private String transcripcion = null;            // Transcripción del episodio
    private boolean reproduciendo = false;
    private boolean pausado = false;
    private boolean descargado = false;

    /**
     * Constructor principal de la clase Podcast.
     *
     * @param titulo Título del episodio
     * @param duracionSegundos Duración en segundos
     * @param creador Creador del podcast
     * @param numeroEpisodio Número del episodio
     * @param temporada Temporada
     * @param categoria Categoría
     * @param description Descripcion
     */
    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria, String description) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.invitados = new ArrayList<>();
        this.descripcion = description;
    }

    /**
     * Constructor para crear un podcast sin creador asignado.
     */
    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        this(titulo, duracionSegundos, creador, numeroEpisodio, temporada, categoria, null);
    }


    // Getters and Setters
    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPodcast getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getInvitados() {
        return new ArrayList<>(invitados);
    }

    public void addInvitados(String invitado) {
        this.invitados.add(invitado);
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
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

    /**
     * Devuelve la descripción del episodio.
     */
    public String obtenerDescripcion() {
        return descripcion;
    }

    /**
     * Agrega un invitado al podcast.
     *
     * @param nombre Nombre del invitado
     */
    public void agregarInvitado(String nombre) {
        this.addInvitados(nombre);
    }

    /**
     * Indica si el episodio pertenece a una temporada nueva.
     */
    public boolean esTemporadaNueva() {
        return temporada >= 1;
    }

    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException {
        if(transcripcion == null) throw new TranscripcionNoDisponibleException();

        return transcripcion;
    }

    public void validarEpisodio() throws EpisodioNoEncontradoException {
        // TODO
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "creador=" + creador +
                ", numeroEpisodio=" + numeroEpisodio +
                ", temporada=" + temporada +
                '}';
    }
}
