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

/**
 * Representa a un usuario con suscripción Premium.
 * Tiene ventajas como reproducción ilimitada, descargas offline y calidad de audio superior.
 */
public class UsuarioPremium extends Usuario{

    // Atributos
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados = new ArrayList<>();
    private String calidadAudio;

    /**
     * Constructor completo para Usuario Premium.
     * @param nombre Nombre.
     * @param email Email.
     * @param password Password.
     * @param suscripcion Tipo específico (Premium, Familiar, etc.).
     */
    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.descargasOffline = descargasOffline;
        this.maxDescargas = maxDescargas;
        this.descargados = new ArrayList<>();
        this.calidadAudio = calidadAudio;
    }

    /**
     * Constructor Premium por defecto.
     * @param nombre Nombre.
     * @param email Email.
     * @param password Password.
     */
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
    /**
     * Reproduce contenido sin límites ni anuncios.
     * Guarda el historial automáticamente.
     * @param contenido Contenido a reproducir
     */
    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        agregarAlHistorial(contenido);
        contenido.reproducir();
    }

    /**
     * Descarga contenido para escucha offline.
     * @param contenido Contenido a descargar.
     * @throws ContenidoYaDescargadoException Si ya estaba descargado.
     */
    public void descargar(Contenido contenido) throws ContenidoYaDescargadoException {

        // Verificamos si ya la tenemos descargada
        for (Contenido descarga: descargados) {
            if(descarga.getId().equals(contenido.getId())){
                throw new ContenidoYaDescargadoException();
            }
        }

         descargados.add(contenido);
    }

    /**
     * Elimina un contenido de la lista de descargas.
     * @param contenido Contenido a borrar.
     */
    public void eliminarDescarga(Contenido contenido) {
         descargados.remove(contenido);
    }

    /**
     * Verifica si se alcanzó el límite de almacenamiento offline.
     * @return true si está lleno.
     */
    public boolean verificarEspacioDescarga(){
         return descargados.size() >= maxDescargas;
    }

    /**
     * Calcula cuántas descargas más puede realizar.
     * @return Espacios libres para descarga.
     */
    public  int getDescargasRestantes() {
        return maxDescargas - descargados.size();
    }

    /**
     * Cambia la calidad de audio de la cuenta.
     * @param calidadAudio Nueva calidad.
     */
    public void cambiarCalidadAudio(String calidadAudio) {
        setCalidadAudio(calidadAudio);
    }

    /**
     * Elimina todos los contenidos descargados.
     */
    public void limpiarDescargas() {
        descargados.clear();
    }

    /**
     * Representación en cadena del usuario premium.
     */
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
