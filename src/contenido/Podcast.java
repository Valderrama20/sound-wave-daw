package contenido;

import enums.CategoriaPodcast;
import interfaces.Descargable;
import interfaces.Reproducible;
import usuarios.Usuario;

import java.util.ArrayList;
import java.util.Date;

/**
 * Representa un episodio de podcast dentro de la plataforma.
 * Contiene información sobre el creador, temporada, categoría y contenido.
 */
public class Podcast extends Contenido implements Reproducible, Descargable {

    // Atributos
    private Creador creador;                 // Creador del podcast (agregación)
    private int numeroEpisodio;              // Número de episodio
    private int temporada;                   // Temporada a la que pertenece
    private String descripcion;              // Descripción del episodio
    private CategoriaPodcast categoria;      // Categoría
    private ArrayList<String> invitados;     // Lista de invitados
    private String transcripcion;            // Transcripción del episodio

    /**
     * Constructor principal de la clase Podcast.
     *
     * @param id Identificador único
     * @param titulo Título del episodio
     * @param reproducciones Número de reproducciones
     * @param likes Número de likes
     * @param duracionSegundos Duración en segundos
     * @param disponible Indica si está disponible
     * @param fechaPublicacion Fecha de publicación
     * @param plataforma Plataforma
     * @param creador Creador del podcast
     * @param numeroEpisodio Número del episodio
     * @param temporada Temporada
     * @param descripcion Descripción
     * @param categoria Categoría
     * @param transcripcion Transcripción
     */
    public Podcast(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion, Plataforma plataforma, Creador creador, int numeroEpisodio, int temporada, String descripcion, CategoriaPodcast categoria, String transcripcion) {
        super(id, titulo, reproducciones, likes, duracionSegundos, disponible, fechaPublicacion, plataforma);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.invitados = new ArrayList<>();
        this.transcripcion = transcripcion;
    }

    /**
     * Constructor para crear un podcast sin creador asignado.
     */
    public Podcast(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion, Plataforma plataforma, int numeroEpisodio, int temporada, String descripcion, CategoriaPodcast categoria, String transcripcion) {
        this(
                id,
                titulo,
                reproducciones,
                likes,
                duracionSegundos,
                disponible,
                fechaPublicacion,
                plataforma,
                null,
                numeroEpisodio,
                temporada,
                descripcion,
                categoria,
                transcripcion
        );
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
        return invitados;
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
        // TODO
        return true;
    }

}
