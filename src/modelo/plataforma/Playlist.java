package modelo.plataforma;

import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import modelo.contenido.Contenido;
import usuarios.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Playlist {

    // Constantes
    private static final int MAX_CONTENIDOS_DEFAULT = 500;

    // Atributos
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private Usuario creador;
    private ArrayList<Contenido> contenidos = new ArrayList<>();
    private boolean esPublica;
    private int seguidores;
    private String descripcion;
    private String portadaURL;
    private Date fechaCreacion = new Date();
    private int maxContenidos = MAX_CONTENIDOS_DEFAULT;

    // Constructor
    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
    }

    public Playlist(String nombre, Usuario creador) {
        this(nombre, creador, false, null);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public ArrayList<Contenido> getContenidos() {
        return new ArrayList<>(contenidos);
    }

    public void addContenido(Contenido contenido) {
        this.contenidos.add(contenido);
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

    public void setMaxContenidos(int maxContenidos) {
        this.maxContenidos = maxContenidos;
    }

    // Metodos
    public void agregarContenido(Contenido contenidoNuevo) throws PlaylistLlenaException, ContenidoDuplicadoException {
        if(maxContenidos >= contenidos.size()) throw new PlaylistLlenaException();

        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(contenidoNuevo.getId())) throw  new ContenidoDuplicadoException();
        }

        addContenido(contenidoNuevo);
    }

    public boolean eliminarContenido(String idContenido) {
        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(idContenido)) {
                contenidos.remove(contenido);
                return true;
            }
        }

        return false;
    }

    public boolean eliminarContenido(Contenido contenidoEliminar) {
        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(contenidoEliminar.getId())) {
                contenidos.remove(contenido);
                return true;
            }
        }

        return false;
    }
}
