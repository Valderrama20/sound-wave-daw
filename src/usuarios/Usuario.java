package usuarios;

import modelo.contenido.Contenido;
import enums.TipoSuscripcion;

import java.util.ArrayList;
import java.util.Date;

public abstract class Usuario {

    // Atributos
    private final String id;
    private String nombre;
    private String email;
    private String password;
    private TipoSuscripcion suscripcion;
    private ArrayList<Playlist> misPlaylist;
    private ArrayList<Contenido> historial;
    private final Date fechaRegisto;
    private Plataforma plataforma;

    // Constructor
    public Usuario(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, Date fechaRegisto, Plataforma plataforma) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
        this.misPlaylist = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.fechaRegisto = fechaRegisto;
        this.plataforma = plataforma;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylist() {
        return misPlaylist;
    }

    public void addPlaylist(Playlist playlist) {
        this.misPlaylist.add(playlist);
    }

    public ArrayList<Contenido> getHistorial() {
        return historial;
    }

    public void addHistorial(Contenido historial) {
        this.historial.add(historial);
    }

    public Date getFechaRegisto() {
        return fechaRegisto;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    // Metodos
    public abstract void reproducir(Contenido contenido);

    public Playlist crearPLaylist(String nombre){
        Playlist newPLaylist = new Playlist(nombre);
        this.addPlaylist(newPLaylist);
        return newPLaylist;
    }

    public void seguirPLaylist(Playlist playlist){
        this.addPlaylist(playlist);
    }

    public void darLike(Contenido contenido){
        // TODO
    }

    public boolean validarEmail(){
        // TODO
        return true;
    }

    public boolean validarPassword(){
        // TODO
        return true;
    }

    public void agregarAlHistorial(Contenido contenido){
        this.addHistorial(contenido);
    }
}
