package modelo;

import contenido.Cancion;
import contenido.Contenido;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

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

    // Constructor
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
        return discografia;
    }

    public void addCancion(Cancion Cancion) {
        this.discografia.add(Cancion);
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
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
    public void publicarCancion(Cancion cancion){
        addCancion(cancion);
    }

    public Album crearAlbum(String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if(!verificado) throw new ArtistaNoVerificadoException();

        for (Album album : albumes){
            if(album.getTitulo().equals(titulo)) throw new AlbumYaExisteException();
        }

        //TODO
        Album newAlbum = new Album();
        addAlbume(newAlbum);
        return newAlbum;
    };

    public ArrayList<Cancion> obtenerTopCanciones(int cantidad) {

        // Hacemos una copia para no mutar el original
        ArrayList<Cancion> discografiaCopia = new ArrayList<>(discografia);

        // Ordenamos por reproducciones
        discografiaCopia.sort(Comparator.comparing(Contenido::getReproducciones).reversed());

        // retornamos la lista recortada
        return (ArrayList<Cancion>) discografiaCopia.subList(0, Math.min(cantidad, discografiaCopia.size()));
    };

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

        return  totalReproducciones;
    }

    public void verificar() {
        verificado = true;
    }

    public void incrementarOyentes() {
        oyentesMensuales++;
    }
}
