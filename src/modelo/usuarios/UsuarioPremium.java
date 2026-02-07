package modelo.usuarios;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import enums.TipoSuscripcion;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioPremium extends Usuario{

    // Atributos
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados = new ArrayList<>();
    private String calidadAudio;

    // Constructor
    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.descargasOffline = descargasOffline;
        this.maxDescargas = maxDescargas;
        this.descargados = new ArrayList<>();
        this.calidadAudio = calidadAudio;
    }

    public UsuarioPremium(String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException {
        this(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

     // Getters and setters
    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return new ArrayList<>(descargados);
    }

    public int getNumDescargados() {
        return descargados.size();
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    // Metodos
    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        // TODO las excepciones
        contenido.reproducir();
    }

    public void descargar(Contenido contenido) throws ContenidoYaDescargadoException {

        // Verificamos si ya la tenemos descargada
        for (Contenido descarga: descargados) {
            if(descarga.getId().equals(contenido.getId())){
                throw new ContenidoYaDescargadoException();
            }
        }

         descargados.add(contenido);
    }

    public void eliminarDescarga(Contenido contenido) {
         descargados.remove(contenido);
    }

    public boolean verificarEspacioDescarga(){
         return descargados.size() < maxDescargas;
    }

    public  int getDescargasRestantes() {
        return maxDescargas - descargados.size();
    }

    public void cambiarCalidadAudio(String calidadAudio) {
        // TODO verificar tipos de calidad de audio
        setCalidadAudio(calidadAudio);
    }

    public void limpiarDescargas() {
        descargados.clear();
    }

    @Override
    public String toString() {
        return "UsuarioPremium{" +
                "descargasOffline=" + descargasOffline +
                ", maxDescargas=" + maxDescargas +
                ", descargados=" + descargados +
                ", calidadAudio='" + calidadAudio + '\'' +
                '}';
    }
}
