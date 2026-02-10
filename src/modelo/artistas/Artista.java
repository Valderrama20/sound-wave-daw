package modelo.artistas;

import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representa a un artista o banda en la plataforma, capaz de publicar álbumes y canciones.
 */
public class Artista {

    // Atributos
    private String id;
    private String nombreArtistico;
    private String nombreReal;
    private String paisOrigen;
    private ArrayList<Cancion> discografia;
    private ArrayList<Album> albumes;
    private int oyentesMensuales = 0;
    private boolean verificado;
    private String biografia;

    /**
     * Constructor completo para crear un artista.
     * @param nombreArtistico Nombre público del artista.
     * @param nombreReal Nombre real de la persona o grupo.
     * @param paisOrigen País de procedencia.
     * @param verificado Estado de verificación en la plataforma.
     * @param biografia Descripción biográfica.
     */
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado, String biografia) {
        this.id = UUID.randomUUID().toString();
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.discografia = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.verificado = verificado;
        this.biografia = biografia;
    }

    /**
     * Crea un artista nuevo sin verificar.
     * @param nombreArtistico Nombre público.
     * @param nombreReal Nombre real.
     * @param paisOrigen País de origen.
     */
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen) {
        this(nombreArtistico, nombreReal, paisOrigen, false, null);
    }

    // Setters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public ArrayList<Cancion> getDiscografia() {
        return new ArrayList<>(discografia);
    }

    public void addCancion(Cancion Cancion) {
        this.discografia.add(Cancion);
    }

    public ArrayList<Album> getAlbumes() {
        return new ArrayList<>(albumes);
    }

    public void addAlbume(Album album) {
        this.albumes.add(album);
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    // Metodos
    /**
     * Publica una canción independiente.
     * @param cancion Canción a publicar.
     */
    public void publicarCancion(Cancion cancion){
        addCancion(cancion);
    }

    /**
     * Crea un nuevo álbum para el artista.
     * @param titulo Título del álbum.
     * @param fecha Fecha de publicación.
     * @return El álbum creado.
     * @throws ArtistaNoVerificadoException Si el artista no está verificado.
     * @throws AlbumYaExisteException Si ya existe un álbum con ese nombre.
     */
    public Album crearAlbum(String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if(!verificado) throw new ArtistaNoVerificadoException();

        for (Album album : albumes){
            if(album.getTitulo().equals(titulo)) throw new AlbumYaExisteException();
        }

        Album newAlbum = new Album(titulo, this, fecha);
        addAlbume(newAlbum);
        return newAlbum;
    };

    /**
     * Obtiene las canciones más populares del artista.
     * @param cantidad Número de canciones a recuperar.
     * @return Lista de canciones ordenadas por reproducciones.
     */
    public ArrayList<Cancion> obtenerTopCanciones(int cantidad) {
        return discografia
                .stream()
                .sorted(Comparator.comparing(Cancion::getReproducciones).reversed())
                .limit(cantidad)
                .collect(Collectors.toCollection(ArrayList::new));
    };

    /**
     * Calcula el promedio de reproducciones de toda la discografía.
     * @return Promedio de reproducciones.
     */
    public double calcularPromedioReproducciones(){
        return (double) getTotalReproducciones() / discografia.size();
    };

    public boolean esVerificado(){
       return verificado;
    };

    public int getTotalReproducciones() {
        int totalReproducciones = 0;

        for(Cancion cancion : discografia) {
            totalReproducciones += cancion.getReproducciones();
        }

        return totalReproducciones;
    }

    public void verificar() {
        verificado = true;
    }

    public void incrementarOyentes() {
        oyentesMensuales++;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nombreArtistico='" + nombreArtistico + '\'' +
                ", nombreReal='" + nombreReal + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", discografia=" + discografia +
                ", albumes=" + albumes +
                ", oyentesMensuales=" + oyentesMensuales +
                ", verificado=" + verificado +
                ", biografia='" + biografia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return oyentesMensuales == artista.oyentesMensuales && verificado == artista.verificado && Objects.equals(id, artista.id) && Objects.equals(nombreArtistico, artista.nombreArtistico) && Objects.equals(nombreReal, artista.nombreReal) && Objects.equals(paisOrigen, artista.paisOrigen) && Objects.equals(discografia, artista.discografia) && Objects.equals(albumes, artista.albumes) && Objects.equals(biografia, artista.biografia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreArtistico, nombreReal, paisOrigen, discografia, albumes, oyentesMensuales, verificado, biografia);
    }
}
