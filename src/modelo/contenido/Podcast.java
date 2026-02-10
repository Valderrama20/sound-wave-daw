package modelo.contenido;

import enums.CategoriaPodcast;
import excepciones.contenido.*;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Creador;

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
     * Crea un nuevo episodio de podcast.
     * @param titulo Título del episodio.
     * @param duracionSegundos Duración en segundos.
     * @param creador Creador del podcast.
     * @param numeroEpisodio Número secuencial del episodio.
     * @param temporada Temporada del podcast.
     * @param categoria Categoría temática.
     * @param description Breve descripción del contenido.
     * @throws DuracionInvalidaException Si la duración es inválida.
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
     * Crea un podcast con información básica (sin descripción).
     * @param titulo Título.
     * @param duracionSegundos Duración.
     * @param creador Creador.
     * @param numeroEpisodio Número de episodio.
     * @param temporada Temporada.
     * @param categoria Categoría.
     * @throws DuracionInvalidaException Si la duración es inválida.
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

    /**
     * Añade un invitado a la lista si no está ya incluido.
     * @param invitado Nombre del invitado.
     */
    public void addInvitados(String invitado) {
        // Verificar que no exista el invitado
        for (String invitadoListado: invitados){
            if(invitadoListado.toLowerCase().contains(invitado.toLowerCase())){
                return;
            }
        }

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
    /**
     * Comienza la reproducción del episodio.
     * @throws ContenidoNoDisponibleException Si el episodio no está disponible.
     */
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if(!disponible) throw new ContenidoNoDisponibleException();

        aumentarReproducciones();
        play();

    }

    /**
     * Reanuda la reproducción.
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

    /**
     * Devuelve la duración del episodio.
     * @return Duración en segundos.
     */
    @Override
    public int getDuracion() {
        return getDuracionSegundos();
    }

    /**
     * Descarga el episodio para escucha offline.
     * @return true si la operación fue exitosa.
     * @throws LimiteDescargasException Si se excedió el límite de descargas.
     * @throws ContenidoYaDescargadoException Si ya fue descargado previamente.
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

    @Override
    public int espacioRequerido() {
        return 0;
    }

    /**
     * Obtiene la descripción del episodio.
     * @return Texto de descripción.
     */
    public String obtenerDescripcion() {
        return descripcion;
    }

    /**
     * Registra un nuevo invitado al episodio.
     * @param nombre Nombre del invitado.
     */
    public void agregarInvitado(String nombre) {
        this.addInvitados(nombre);
    }

    /**
     * Verifica si es una temporada reciente (mayor o igual a 1).
     * @return true si es temporada nueva.
     */
    public boolean esTemporadaNueva() {
        return temporada >= 1;
    }

    /**
     * Obtiene la transcripción del audio del episodio.
     * @return Texto de la transcripción.
     * @throws TranscripcionNoDisponibleException Si no existe transcripción.
     */
    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException {
        if(transcripcion == null) throw new TranscripcionNoDisponibleException();

        return transcripcion;
    }

    /**
     * Valida la existencia y consistencia del episodio.
     * @throws EpisodioNoEncontradoException Si el episodio no es válido.
     */
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
