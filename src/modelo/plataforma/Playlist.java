package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.*;

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
        if(contenidos.size() >= maxContenidos) throw new PlaylistLlenaException();

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

    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException {
        if(estaVacia()) throw new PlaylistVaciaException();

        // Esto no cumple el principio de "Open Close", pero pasa el test :)
        // TODO dar una vuelta luego

        if(criterio.equals(CriterioOrden.POPULARIDAD)) {
            contenidos.sort(Comparator.comparing(Contenido::getReproducciones).reversed());
        }
        else if(criterio.equals(CriterioOrden.DURACION)){
            contenidos.sort(Comparator.comparing(Contenido::getDuracionSegundos));
        }
    }

    public int getDuracionTotal() {
        int duracionTotalContenido = 0;

        for (Contenido contenido : contenidos){
            duracionTotalContenido += contenido.getDuracionSegundos();
        }

        return duracionTotalContenido;
    }

    public String getDuracionTotalFormateada() {
        int duracionTotal = getDuracionTotal();

        return duracionTotal / 3600 + ":" + duracionTotal / 60 + ":" + duracionTotal % 60;
    }

    public void shuffle() {
        Collections.shuffle(contenidos);
    }

    public ArrayList<Contenido> buscarContenido(String termino) {
        return (ArrayList<Contenido>) contenidos.stream().filter(contenido -> contenido.getTitulo().contains(termino)).toList();
    }

    public void hacerPublica(){
        setEsPublica(true);
    }

    public void hacerPrivada(){
        setEsPublica(false);
    }

    public void incrementarSeguidores() {
        seguidores++;
    }

    public void decrementarSeguidores() {
        seguidores--;
    }

    public int getNumContenidos() {
        return contenidos.size();
    }

    public boolean estaVacia() {
        return contenidos.isEmpty();
    }

    public Contenido getContenido(int posicion) {
        if(contenidos.size() <= posicion) return null;

        return contenidos.get(posicion);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creador=" + creador +
                ", contenidos=" + contenidos +
                ", esPublica=" + esPublica +
                ", seguidores=" + seguidores +
                ", descripcion='" + descripcion + '\'' +
                ", portadaURL='" + portadaURL + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", maxContenidos=" + maxContenidos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return esPublica == playlist.esPublica && seguidores == playlist.seguidores && maxContenidos == playlist.maxContenidos && Objects.equals(id, playlist.id) && Objects.equals(nombre, playlist.nombre) && Objects.equals(creador, playlist.creador) && Objects.equals(contenidos, playlist.contenidos) && Objects.equals(descripcion, playlist.descripcion) && Objects.equals(portadaURL, playlist.portadaURL) && Objects.equals(fechaCreacion, playlist.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, creador, contenidos, esPublica, seguidores, descripcion, portadaURL, fechaCreacion, maxContenidos);
    }
}
