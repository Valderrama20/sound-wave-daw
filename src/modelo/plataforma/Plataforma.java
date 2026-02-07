package modelo.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

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

        // Verificar que el email no este uso
        if(usuariosPorEmail.containsKey(email)) throw new UsuarioYaExisteException();

        // Crear el usuario
        UsuarioPremium newUser = new UsuarioPremium(nombre, email, password, tipo);

        // Agregar el nuevo usuario a la lista
        usuariosPorEmail.put(email, newUser);

        // Retornal el usuario creado
        return newUser;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return registrarUsuarioPremium(nombre,email,password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        // Verificar que el email no este uso
        if(usuariosPorEmail.containsKey(email)) throw new UsuarioYaExisteException();

        // Crear el usuario
        UsuarioGratuito newUser = new UsuarioGratuito(nombre, email, password);

        // Agregar el nuevo usuario a la lista
        usuariosPorEmail.put(email, newUser);

        // Retornal el usuario creado
        return newUser;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium() {
        // Crear nuevo array list para los usuarios premium
        ArrayList<UsuarioPremium> usuariosPremium = new ArrayList<>();

        // Recorrer y seleccionar solo los premium
        // Utilizamos ".values()" para obtener solo los valores del hashMap y poder recorrerlos
        for(Usuario usuario: usuariosPorEmail.values()) {
            if(usuario instanceof UsuarioPremium usuarioPremium){
                usuariosPremium.add(usuarioPremium);
            }
        }

        // Retornar usuarios premium
        return usuariosPremium;
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        // Crear nuevo array list para los usuarios gratuitos
        ArrayList<UsuarioGratuito> usuariosGratuitos = new ArrayList<>();

        // Recorrer y seleccionar solo los gratuitos
        for(Usuario usuario: usuariosPorEmail.values()) {
            if(usuario instanceof UsuarioGratuito usuarioGratuito){
                usuariosGratuitos.add(usuarioGratuito);
            }
        }

        // Retornar los usuarios gratuitos
        return usuariosGratuitos;
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        // Crear un nuevo array list con los valores del hashMap y retornar
        return new ArrayList<>(usuariosPorEmail.values());
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return null;
    }

    // Gestión de artistas
    public Artista registrarArtista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado) {
        // Crear nuevo artista
        Artista newArtista = new Artista(nombreArtistico,nombreReal, paisOrigen, verificado, null);

        // Agregar el artista a la lista
        artistas.put(nombreArtistico, newArtista);

        // Retornar el artista
        return newArtista;
    }

    public void registrarArtista(Artista artista) {
    }

    public ArrayList<Artista> getArtistasVerificados() {
        // Crear nuevo array para los artistas verificados
        ArrayList<Artista> artistasVerificados = new ArrayList<>();

        // Recorrer el hashMap y obtener solo los artistas verificados
        for(Artista artista: artistas.values()){
            if(artista.isVerificado()) {
                artistasVerificados.add(artista);
            }
        }

        // Retornar artistas verificados
        return artistasVerificados;
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        // Crear nuevo array para los artistas no verificados
        ArrayList<Artista> artistasNoVerificados = new ArrayList<>();

        // Recorrer el hashMap y obtener solo los artistas no verificados
        for(Artista artista: artistas.values()){
            if(!artista.isVerificado()) {
                artistasNoVerificados.add(artista);
            }
        }

        // Retornar artistas no verificados
        return artistasNoVerificados;
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException {

        return artistas
                .values()
                .stream()
                .filter(a -> a.getNombreArtistico().toLowerCase().contains(nombre.toLowerCase()))
                .findFirst()
                .orElseThrow(ArtistaNoEncontradoException::new);
    }

    // Gestión de álbumes
    public Album crearAlbum(Artista artista, String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        // Crear un nuevo album
        Album newAlbum = artista.crearAlbum(titulo, fecha);

        // Agregar el nuevo album a la lista
        albumes.add(newAlbum);

        // Retornar el nuevo album
        return newAlbum;
    }

    public ArrayList<Album> getAlbumes() {
        return new ArrayList<>();
    }

    // Gestión de canciones
    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        // Crear cancion y retornarla
        Cancion newCancion = new Cancion(titulo, duracion, artista, genero);

        // Agregar al catalogo
        agregarContenidoCatalogo(newCancion);

        // Retornar cancion creada
        return newCancion;
    }

    public Cancion crearCancionEnAlbum(String titulo, int duracion, Artista artista, GeneroMusical genero, Album album) throws DuracionInvalidaException, AlbumCompletoException {
        return null;
    }

    public void agregarContenidoCatalogo(Contenido contenido) {
        catalogo.add(contenido);
    }

    public ArrayList<Cancion> getCanciones() {
        // Crear array list para las canciones
        ArrayList<Cancion> canciones = new ArrayList<>();

        // Recorrer los albumes y obtener las canciones
        for (Album album : albumes) {

            // Utilizamos "addAll" para poder agregar una lista entera
            canciones.addAll(album.getCanciones());
        }

        // Retornamos las canciones
        return canciones;
    }

    // Gestión de creadores/podcasts
    public Creador registrarCreador(String nombreCanal, String nombre, String descripcion) {
        // Crear nuevo creador
        Creador newCreador = new Creador(nombreCanal, nombre, descripcion);

        // Agregar al nuevo creado a la lista
        creadores.put(nombreCanal, newCreador);

        // Retornar nuevo creado
        return newCreador;
    }

    public void registrarCreador(Creador creador) {
    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador, int numEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException, LimiteEpisodiosException {
        // Crear nuevo podcast
        Podcast newPodcast = new Podcast(titulo, duracion, creador, numEpisodio, temporada, categoria);

        // Publicar el podcast
        creador.publicarPodcast(newPodcast);

        // Agregar al catalogo
        agregarContenidoCatalogo(newPodcast);

        // Retornar el podcast creado
        return newPodcast;

    }

    public ArrayList<Podcast> getPodcasts() {
        // Crear nuevo array para los podcasts
        ArrayList<Podcast> allPodcasts = new ArrayList<>();

        // Recorrer los creadores y obtener sus podcasts
        for (Creador creador : creadores.values()) {
            allPodcasts.addAll(creador.getEpisodios());
        }

        // Retornar todos los podcasts
        return allPodcasts;
    }

    public ArrayList<Creador> getTodosLosCreadores() {
        return new ArrayList<>(creadores.values());
    }

    // Gestión de playlists públicas
    public Playlist crearPlaylistPublica(String nombre, Usuario creador) {
        // Crear nueva playlist
        Playlist newPLaylist = creador.crearPlaylist(nombre);

        // Modificamos su visibilidad porque su valor por defecto es privada
        newPLaylist.setEsPublica(true);

        // Agregarla a la lista
        playlistsPublicas.add(newPLaylist);

        // Retornar playList creada
        return newPLaylist;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return playlistsPublicas;
    }

    // Búsquedas
    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException {
        // Crear nuevo array list para el contenido de conincida
        ArrayList<Contenido> contenidoEncontrado = new ArrayList<>();

        // Recorrer el contenido y obtener solo los que tengan coincidencias
        for (Contenido contenido : catalogo) {

            // Trasformar el título y termino a minúsculas para búsqueda insensitive
            if(contenido.getTitulo().toLowerCase().contains(termino.toLowerCase())){
                contenidoEncontrado.add(contenido);
            }
        }

        // Tirar una excecion
        if(contenidoEncontrado.isEmpty()) throw new ContenidoNoEncontradoException();

        // Retornar el resultado de la busqueda
        return contenidoEncontrado;
    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws ContenidoNoEncontradoException {
        // Crear nuevo array list para las canciones con el género indicado
        ArrayList<Cancion> cancionesEncontrado = new ArrayList<>();

        // obtener las canciones y luego recorrerlas para seleccionar las del genero indicado
        for (Cancion cancion : getCanciones()) {
            if(cancion.getGenero().equals(genero)){
                cancionesEncontrado.add(cancion);
            }
        }

        // Si no se encuentra nada, tirar una excepcion
        if(cancionesEncontrado.isEmpty()) throw new ContenidoNoEncontradoException();

        // Retornar el resultado de la busqueda
        return cancionesEncontrado;
    }

    public ArrayList<Podcast> buscarPorCategoria(CategoriaPodcast categoria) throws ContenidoNoEncontradoException {
        // Crear array list para los podcasts que coincidan
        ArrayList<Podcast> podcastsEncontrados = new ArrayList<>();

        // Obtener lod podcasts y obtener los que tengan la categoria buscada
        for (Podcast podcast : getPodcasts()){
            if(podcast.getCategoria().equals(categoria)){
                podcastsEncontrados.add(podcast);
            }
        }

        // Retornar los resultados
        return podcastsEncontrados;
    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad) {
//        // Crear una copia del array list para no mutar el original
//        ArrayList<Contenido> copiaContenido = new ArrayList<>(catalogo);
//
//        // Ordenamos por mayor cantidad de reproducciones
//        copiaContenido.sort(Comparator.comparing(Contenido::getReproducciones).reversed());
//
//        // devolvemos solo la cantidad indicada
//        return new ArrayList<>(copiaContenido.subList(0, Math.min(cantidad, copiaContenido.size())));

        // Aplicamos la misma logica pero utilizando streams
        // Esto transforma el array list en un flujo se datos
        // luego indicamos que los vamos a ordenar
        // después indicamos la cantidad de elementos que queremos
        // por último metemos los elementos en nuevo array list
        return  catalogo.stream()
                .sorted(Comparator.comparing(Contenido::getReproducciones).reversed())
                .limit(cantidad)
                .collect(Collectors.toCollection(ArrayList::new));
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
