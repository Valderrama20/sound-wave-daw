package modelo.plataforma;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import enums.CategoriaPodcast;
import enums.GeneroMusical;
import enums.TipoSuscripcion;

import excepciones.artista.AlbumCompletoException;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.plataforma.ArtistaNoEncontradoException;
import excepciones.plataforma.ContenidoNoEncontradoException;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;

import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;

import utilidades.RecomendadorIA;

public class Plataforma {

    private static Plataforma instancia;
    private String nombre;
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistsPublicas;
    private HashMap<String, Artista> artistas;
    private HashMap<String, Creador> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;

    private Plataforma(String nombre) {
        this.nombre = nombre;
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.catalogo = new ArrayList<>();
        this.playlistsPublicas = new ArrayList<>();
        this.artistas = new HashMap<>();
        this.creadores = new HashMap<>();
        this.albumes = new ArrayList<>();
        this.anuncios = new ArrayList<>();
        this.recomendador = new RecomendadorIA();
        this.totalAnunciosReproducidos = 0;
    }

    public static synchronized Plataforma getInstancia(String nombre) {
        if (instancia == null) {
            instancia = new Plataforma(nombre);
        }
        return instancia;
    }

    public static synchronized Plataforma getInstancia() {
        if (instancia == null) {
            instancia = new Plataforma("SoundWave");
        }
        return instancia;
    }

    public static synchronized void reiniciarInstancia() {
        instancia = null;
    }

    // Gestión de usuarios
    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium() {
        return new ArrayList<>();
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        return new ArrayList<>();
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        return new ArrayList<>();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return null;
    }

    // Gestión de artistas
    public Artista registrarArtista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado) {
        return null;
    }

    public void registrarArtista(Artista artista) {
    }

    public ArrayList<Artista> getArtistasVerificados() {
        return new ArrayList<>();
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        return new ArrayList<>();
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException {
        return null;
    }

    // Gestión de álbumes
    public Album crearAlbum(Artista artista, String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        return null;
    }

    public ArrayList<Album> getAlbumes() {
        return new ArrayList<>();
    }

    // Gestión de canciones
    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        return null;
    }

    public Cancion crearCancionEnAlbum(String titulo, int duracion, Artista artista, GeneroMusical genero, Album album) throws DuracionInvalidaException, AlbumCompletoException {
        return null;
    }

    public void agregarContenidoCatalogo(Contenido contenido) {
    }

    public ArrayList<Cancion> getCanciones() {
        return new ArrayList<>();
    }

    // Gestión de creadores/podcasts
    public Creador registrarCreador(String nombreCanal, String nombre, String descripcion) {
        return null;
    }

    public void registrarCreador(Creador creador) {
    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador, int numEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException, LimiteEpisodiosException {
        return null;
    }

    public ArrayList<Podcast> getPodcasts() {
        return new ArrayList<>();
    }

    public ArrayList<Creador> getTodosLosCreadores() {
        return new ArrayList<>();
    }

    // Gestión de playlists públicas
    public Playlist crearPlaylistPublica(String nombre, Usuario creador) {
        return null;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return new ArrayList<>();
    }

    // Búsquedas
    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException {
        return new ArrayList<>();
    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws ContenidoNoEncontradoException {
        return new ArrayList<>();
    }

    public ArrayList<Podcast> buscarPorCategoria(CategoriaPodcast categoria) throws ContenidoNoEncontradoException {
        return new ArrayList<>();
    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad) {
        return new ArrayList<>();
    }

    // Anuncios
    public Anuncio obtenerAnuncioAleatorio() {
        return null;
    }

    public void incrementarAnunciosReproducidos() {
    }

    // Estadísticas
    public String obtenerEstadisticasGenerales() {
        return "";
    }

    // Getters básicos
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Contenido> getCatalogo() {
        return new ArrayList<>(catalogo);
    }

    public HashMap<String, Artista> getArtistas() {
        return new HashMap<>(artistas);
    }

    public HashMap<String, Creador> getCreadores() {
        return new HashMap<>(creadores);
    }

    public ArrayList<Anuncio> getAnuncios() {
        return new ArrayList<>(anuncios);
    }

    public RecomendadorIA getRecomendador() {
        return recomendador;
    }

    public int getTotalUsuarios() {
        return 0;
    }

    public int getTotalContenido() {
        return 0;
    }

    public int getTotalAnunciosReproducidos() {
        return totalAnunciosReproducidos;
    }

    @Override
    public String toString() {
        return "Plataforma: " + nombre;
    }
}
