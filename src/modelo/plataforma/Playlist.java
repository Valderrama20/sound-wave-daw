package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.*;

/**
 * Representa una lista de reproducción de contenido multimedia.
 * Puede contener canciones y podcasts, y ser pública o privada.
 */
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

    /**
     * Constructor completo de Playlist.
     * @param nombre Nombre de la lista.
     * @param creador Usuario propietario.
     * @param esPublica Visibilidad de la lista.
     * @param descripcion Descripción opcional.
     */
    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
    }

    /**
     * Constructor para una playlist privada básica.
     * @param nombre Nombre de la lista.
     * @param creador Usuario propietario.
     */
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
    /**
     * Añade un contenido a la playlist.
     * @param contenidoNuevo Contenido a agregar.
     * @throws PlaylistLlenaException Si se ha alcanzado el límite máximo de contenidos.
     * @throws ContenidoDuplicadoException Si el contenido ya existe en la lista.
     */
    public void agregarContenido(Contenido contenidoNuevo) throws PlaylistLlenaException, ContenidoDuplicadoException {
        if(contenidos.size() >= maxContenidos) throw new PlaylistLlenaException();

        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(contenidoNuevo.getId())) throw  new ContenidoDuplicadoException();
        }

        addContenido(contenidoNuevo);
    }

    /**
     * Elimina un contenido de la playlist por su ID.
     * @param idContenido ID del contenido a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró.
     */
    public boolean eliminarContenido(String idContenido) {
        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(idContenido)) {
                contenidos.remove(contenido);
                return true;
            }
        }

        return false;
    }

    /**
     * Elimina un objeto contenido de la playlist.
     * @param contenidoEliminar Objeto contenido.
     * @return true si se eliminó.
     */
    public boolean eliminarContenido(Contenido contenidoEliminar) {
        for (Contenido contenido : contenidos) {
            if(contenido.getId().equals(contenidoEliminar.getId())) {
                contenidos.remove(contenido);
                return true;
            }
        }

        return false;
    }

    /**
     * Ordena los contenidos de la playlist según un criterio específico.
     * @param criterio Criterio de ordenación (Popularidad, Duración, etc.).
     * @throws PlaylistVaciaException Si la playlist no tiene elementos para ordenar.
     */
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

    /**
     * Calcula la duración total de reproducción de la playlist en segundos.
     * @return Duración total.
     */
    public int getDuracionTotal() {
        int duracionTotalContenido = 0;

        for (Contenido contenido : contenidos){
            duracionTotalContenido += contenido.getDuracionSegundos();
        }

        return duracionTotalContenido;
    }

    /**
     * Obtiene la duración total en formato legible (h:m:s).
     * @return String con duración formateada.
     */
    public String getDuracionTotalFormateada() {
        int duracionTotal = getDuracionTotal();

        return duracionTotal / 3600 + ":" + duracionTotal / 60 + ":" + duracionTotal % 60;
    }

    /**
     * Mezcla aleatoriamente el orden de los contenidos.
     */
    public void shuffle() {
        Collections.shuffle(contenidos);
    }

    /**
     * Busca contenidos dentro de la playlist cuyo título contenga el término dado.
     * @param termino Texto a buscar.
     * @return Lista de contenidos coincidentes.
     */
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
