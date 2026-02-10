package modelo.contenido;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase abstracta que define los atributos y comportamientos comunes de todo el contenido multimedia.
 */
public abstract class Contenido {

    // Atributos
    protected String id;                        // Identificador único del contenido.
    protected String titulo;                    // Título del contenido
    protected int reproducciones;               // Contador de reproducciones
    protected int likes;                        // Contador de “me gusta”
    protected int duracionSegundos;             // Duración (en segundos).
    protected ArrayList<String> tags;           // Etiquetas asociadas al contenido.
    protected boolean disponible;               // Indica si el contenido está disponible.
    protected Date fechaPublicacion;            // Fecha de publicación

    /**
     * Constructor base para inicializar un contenido.
     * @param titulo Título del contenido.
     * @param duracionSegundos Duración en segundos.
     * @throws DuracionInvalidaException Si la duración es menor o igual a cero.
     */
    public Contenido(String titulo, int duracionSegundos) throws DuracionInvalidaException {

        // validaciones
        if (duracionSegundos <= 0) throw new DuracionInvalidaException();

        // asignación de valores
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.reproducciones = 0;
        this.likes = 0;
        this.duracionSegundos = duracionSegundos;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public int getLikes() {
        return likes;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public ArrayList<String> getTags() {
        return new ArrayList<>(tags); // copia defensiva
    }


    public boolean isDisponible() {
        return disponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Metodos
    /**
     * Método abstracto para iniciar la reproducción del contenido.
     * @throws ContenidoNoDisponibleException Si el contenido no está disponible para reproducción.
     */
    public abstract void reproducir() throws ContenidoNoDisponibleException;

    /**
     * Incrementa el contador de reproducciones en uno.
     */
    public void aumentarReproducciones() {
        reproducciones++;
    }

    /**
     * Incrementa el contador de "me gusta".
     */
    public void agregarLike() {
        likes++;
    }

    /**
     * Verifica si el contenido es considerado popular.
     * @return true si tiene más de 100,000 reproducciones.
     */
    public boolean esPopular() {
        return reproducciones > 100000;
    }

    /**
     * Valida si la duración del contenido es correcta.
     * @throws DuracionInvalidaException Si la duración es inválida.
     */
    public void validarDuracion() throws DuracionInvalidaException {
        if (duracionSegundos <= 0) throw new DuracionInvalidaException();
    }

    /**
     * Asocia una nueva etiqueta si no existe previamente.
     * @param tag Etiqueta a añadir.
     */
    public void agregarTag(String tag) {
        if (!this.tieneTag(tag)) {
            this.tags.add(tag);
        }
    }

    public boolean tieneTag(String tag) {
        for (String localTag : tags) {
            if (tag.equalsIgnoreCase(localTag)) return true;
        }

        return false;
    }

    public void marcarNoDisponible() {
        disponible = false;
    }

    public void marcarDisponible() {
        disponible = true;
    }

    public String getDuracionFormateada() {
        return duracionSegundos / 60 + ":" + duracionSegundos % 60;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", reproducciones=" + reproducciones +
                ", likes=" + likes +
                ", duracionSegundos=" + duracionSegundos +
                ", tags=" + tags +
                ", disponible=" + disponible +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contenido contenido = (Contenido) o;
        return Objects.equals(id, contenido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
