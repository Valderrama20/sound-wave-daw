package modelo.plataforma;

import enums.TipoSuscripcion;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioPremium;

import java.util.ArrayList;
import java.util.HashMap;

public class Plataforma {

    // Atributos
    private static Plataforma instance;

    private String nombre;
    private HashMap<String, Usuario> usuarios = new HashMap<>();
    private HashMap<String, Usuario> usuariosPorEmail = new HashMap<>();
    private ArrayList<Contenido> catalogo = new ArrayList<>();
    private ArrayList<Playlist> playlistsPublicas = new ArrayList<>();
    private HashMap<String, Artista> artistas = new HashMap<>();
    private HashMap<String, Creador> creadores = new HashMap<>();
    private ArrayList<Album> albumes = new ArrayList<>();
    private ArrayList<Anuncio> anuncios = new ArrayList<>();
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;

    // Construcotor
    private Plataforma(String nombre) {
        this.nombre = nombre;
    }

    // Métodos Singleton:
    public static synchronized Plataforma getInstancia(String nombre) {
        if(instance == null) {
            instance = new Plataforma(nombre);
        }

        return instance;
    }

    public static synchronized Plataforma getInstancia() {
        return getInstancia("default name");
    }

    public static synchronized void reiniciarInstancia() {
        // TODO
    }

    // Getters and Setters
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
        return usuarios.size();
    }

    public int getTotalContenido() {
        return catalogo.size();
    }

    public int getTotalAnunciosReproducidos() {
        return totalAnunciosReproducidos;
    }

    // Metodos

    // Gestión de usuarios:

    // Registra usuario premium garantizando email único.
    UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        // verificamos si el gmail ya esta en uso
        if(usuarios.containsKey(email)) throw new UsuarioYaExisteException();

        UsuarioPremium newUser = new UsuarioPremium()
    }
}
