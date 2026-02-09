package modelo.usuarios;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import enums.TipoSuscripcion;
import modelo.plataforma.Plataforma;
import modelo.plataforma.Playlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase abstracta base que representa a un usuario de la plataforma.
 * Contiene la información básica, credenciales y gestión de playlists y likes.
 */
public abstract class Usuario {

    // Atributos
    private final String id;
    private String nombre;
    private String email;
    private String password;
    private TipoSuscripcion suscripcion;
    private ArrayList<Playlist> misPlaylist = new ArrayList<>();
    private ArrayList<Contenido> historial = new ArrayList<>();
    private final Date fechaRegisto = new Date();
    private ArrayList<Playlist> playlistsSeguidas = new ArrayList<>();
    private ArrayList<Contenido> contenidosLiked = new ArrayList<>();

    /**
     * Constructor principal de Usuario.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico (debe ser válido).
     * @param password Contraseña (debe cumplir requisitos de seguridad).
     * @param suscripcion Tipo de suscripción inicial.
     * @throws EmailInvalidoException Si el email tiene formato incorrecto.
     * @throws PasswordDebilException Si password es insegura.
     */
    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        validarEmail(email);
        validarPassword(password);

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidoException {
        validarEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordDebilException  {
        validarPassword(password);
        this.password = password;
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylists() {
        return new ArrayList<>(misPlaylist);
    }

    public ArrayList<Contenido> getHistorial() {
        return new ArrayList<>(historial);
    }

    public Date getFechaRegisto() {
        return fechaRegisto;
    }

    /**
     * Devuelve las playlists que sigue el usuario.
     * @return Lista de playlists seguidas.
     */
    public ArrayList<Playlist> getPlaylistsSeguidas() {
        return new ArrayList<>(playlistsSeguidas);
    }

    public ArrayList<Contenido> getContenidosLiked() {
        return new ArrayList<>(contenidosLiked);
    }

    // Metodos
    /**
     * Método abstracto para reproducir contenido.
     * La implementación varía según si es Premium o Gratuito.
     * @param contenido Contenido a reproducir.
     * @throws ContenidoNoDisponibleException Contenido no válido.
     * @throws LimiteDiarioAlcanzadoException Si usuario gratuito excede límite.
     * @throws AnuncioRequeridoException Si usuario gratuito debe ver anuncio.
     */
    public abstract void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;

    /**
     * Crea una nueva playlist propia.
     * @param nombre Nombre de la lista.
     * @return Playlist creada.
     */
    public Playlist crearPlaylist(String nombre){
        Playlist newPLaylist = new Playlist(nombre, this);
        misPlaylist.add(newPLaylist);
        return newPLaylist;
    }

    /**
     * Sigue una playlist pública.
     * @param playlist Playlist a seguir.
     */
    public void seguirPlaylist(Playlist playlist){
        playlist.incrementarSeguidores();
        playlistsSeguidas.add(playlist);
    }

    /**
     * Deja de seguir una playlist.
     * @param playlist Playlist a dejar.
     */
    public void dejarDeSeguirPlaylist(Playlist playlist){
        playlistsSeguidas.remove(playlist);
    }

    /**
     * Da 'me gusta' a un contenido.
     * @param contenido Contenido.
     */
    public void darLike(Contenido contenido){
        contenido.agregarLike();
        contenidosLiked.add(contenido);
    }

    /**
     * Quita 'me gusta' a un contenido.
     * @param contenido Contenido.
     */
    public void quitarLike(Contenido contenido) {
        contenidosLiked.remove(contenido);
    }

    /**
     * Valida formato de email.
     * @param email Email a comprobar.
     * @return true si es válido.
     * @throws EmailInvalidoException Si el formato es incorrecto.
     */
    public boolean validarEmail(String email) throws EmailInvalidoException{
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(email == null || email.isEmpty() || !email.matches(regex)) {
            throw new EmailInvalidoException();
        }

        return true;
    }

    /**
     * Valida seguridad de contraseña (mínimo 8 caracteres, números, etc).
     * @param password Password a comprobar.
     * @return true si es válida.
     * @throws PasswordDebilException Si es muy débil.
     */
    public boolean validarPassword(String password) throws PasswordDebilException{
        if (password == null || password.isEmpty()) {
            throw new PasswordDebilException("La contraseña está vacía");
        }

        String regex =
                        "^(?=.*[a-z])" +      // minúscula
                        //"(?=.*[A-Z])" +       // mayúscula
                        "(?=.*\\d)" +         // número
                        //"(?=.*[@$!%*?&#])" +  // especial
                        ".{8,}$";             // mínimo 8

        if (!password.matches(regex)) {
            throw new PasswordDebilException(
                    "La contraseña debe tener mínimo 8 caracteres, mayúscula, minúscula, número y símbolo"
            );
        }

        return true;
    }

    /**
     * Agrega un contenido al historial de reproducción.
     * @param contenido Contenido reproducido.
     */
    public void agregarAlHistorial(Contenido contenido){
        historial.add(contenido);
    }

    /**
     * Limpia completamente el historial de reproducción.
     */
    public void limpiarHistorial() {
        historial.clear();
    }

    /**
     * Verifica si el usuario tiene suscripción Premium.
     * @return true si es Premium.
     */
    public boolean esPremium() {
        return suscripcion.equals(TipoSuscripcion.PREMIUM);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", suscripcion=" + suscripcion +
                ", misPlaylist=" + misPlaylist +
                ", historial=" + historial +
                ", fechaRegisto=" + fechaRegisto +
                ", playlistsSeguidas=" + playlistsSeguidas +
                ", contenidosLiked=" + contenidosLiked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && suscripcion == usuario.suscripcion && Objects.equals(misPlaylist, usuario.misPlaylist) && Objects.equals(historial, usuario.historial) && Objects.equals(fechaRegisto, usuario.fechaRegisto) && Objects.equals(playlistsSeguidas, usuario.playlistsSeguidas) && Objects.equals(contenidosLiked, usuario.contenidosLiked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, password, suscripcion, misPlaylist, historial, fechaRegisto, playlistsSeguidas, contenidosLiked);
    }
}
