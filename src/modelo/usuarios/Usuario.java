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

    // Constructor
    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        validarEmail(email);
        validarPassword(password);

        // TODO validas usuario y contraseña
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

    public ArrayList<Playlist> getPlaylistsSeguidas() {
        return new ArrayList<>(playlistsSeguidas);
    }

    public ArrayList<Contenido> getContenidosLiked() {
        return new ArrayList<>(contenidosLiked);
    }

    // Metodos
    public abstract void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;

    public Playlist crearPlaylist(String nombre){
        Playlist newPLaylist = new Playlist(nombre, this);
        misPlaylist.add(newPLaylist);
        return newPLaylist;
    }

    public void seguirPlaylist(Playlist playlist){
        playlistsSeguidas.add(playlist);
    }

    public void dejarDeSeguirPlaylist(Playlist playlist){
        playlistsSeguidas.remove(playlist);
    }

    public void darLike(Contenido contenido){
        contenido.agregarLike();
        contenidosLiked.add(contenido);
    }

    public void quitarLike(Contenido contenido) {
        contenidosLiked.remove(contenido);
    }

    public boolean validarEmail(String email) throws EmailInvalidoException{
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(email == null || email.isEmpty() || !email.matches(regex)) {
            throw new EmailInvalidoException();
        }

        return true;
    }

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

    public void agregarAlHistorial(Contenido contenido){
        historial.add(contenido);
    }
    public void limpiarHistorial() {
        historial.clear();
    }

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
