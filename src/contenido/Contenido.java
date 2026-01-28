package contenido;

import java.util.ArrayList;
import java.util.Date;

public abstract class Contenido {

    // Atributos
    private final String id;
    private String titulo;
    private int reproducciones;
    private int likes;
    private int duracionSegundos;
    private ArrayList<String> tags;
    private boolean disponible;
    private Date fechaPublicacion;

    // Constructor
    public Contenido(String id, String titulo, int reproducciones, int likes, int duracionSegundos, boolean disponible, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.reproducciones = reproducciones;
        this.likes = likes;
        this.duracionSegundos = duracionSegundos;
        this.tags = new ArrayList<>();
        this.disponible = disponible;
        this.fechaPublicacion = fechaPublicacion;
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

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void addTag(String tags) {
        this.tags.add(tags);
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Metodos
    public abstract void reproducir();

    public void aumentarReproducciones() {
        reproducciones++;
    };

    public void agregarLike() {
        likes++;
    };

    public boolean esPopular() {
       return
    };
}
