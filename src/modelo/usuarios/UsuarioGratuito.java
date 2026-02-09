package modelo.usuarios;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import enums.TipoSuscripcion;
import modelo.plataforma.Anuncio;

import java.util.Date;

/**
 * Representa a un usuario con suscripción gratuita.
 * Tiene limitaciones como anuncios periódicos y un límite de reproducciones diarias.
 */
public class UsuarioGratuito extends Usuario {

    // Atributos
    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    private final int LIMITE_DIARIO = 50;
    private final int CANCIONES_ENTRE_ANUNCIOS = 3;

    /**
     * Constructor para usuario gratuito.
     * @param nombre Nombre del usuario.
     * @param email Email.
     * @param password Contraseña.
     */
    public UsuarioGratuito(String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException {
        super( nombre, email, password, TipoSuscripcion.GRATUITO);
        this.limiteReproducciones = LIMITE_DIARIO;
    }

    // Getters and setters
    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    // Metodos
    /**
     * Intenta reproducir un contenido verificando todas las restricciones.
     * @param contenido Contenido a reproducir.
     * @throws ContenidoNoDisponibleException Si el contenido no está activo.
     * @throws LimiteDiarioAlcanzadoException Si superó el límite diario de la cuenta gratuita.
     * @throws AnuncioRequeridoException Si se ha superado el número de canciones sin anuncio.
     */
    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException
    {
        if(!contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException();
        }

        if(!puedeReproducir()){
            throw new LimiteDiarioAlcanzadoException();
        }

        if (debeVerAnuncio()) {
            throw new AnuncioRequeridoException();
        }

        reproduccionesHoy++;
        cancionesSinAnuncio++;
        fechaUltimaReproduccion = new Date();
        contenido.reproducir();
    }

    /**
     * Consume un anuncio publicitario y resetea el contador para el siguiente anuncio.
     * @param anuncio Anuncio a ver/escuchar.
     */
    public void verAnuncio(Anuncio anuncio) {
        anunciosEscuchados++;
        cancionesSinAnuncio = 0;
        ultimoAnuncio = new Date();

        System.out.println("Viendo un anuncio...");
    }

    /**
     * Verifica si no ha superado el límite diario.
     */
    public boolean puedeReproducir() {
        return reproduccionesHoy < LIMITE_DIARIO;
    }

    /**
     * Verifica si toca ver anuncio según las canciones reproducidas seguidas.
     */
    public boolean debeVerAnuncio(){
        return cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS ;
    }

    /**
     * Reinicia el contador de reproducciones diarias (se usaría al cambiar de día).
     */
    public void reiniciarContadorDiario() {
        reproduccionesHoy = 0;
    }

    /**
     * Calcula cuántas reproducciones le quedan disponibles hoy.
     * @return Reproducciones restantes.
     */
    public int getReproduccionesRestantes() {
        return limiteReproducciones - reproduccionesHoy;
    }

    /**
     * Devuelve el contador actual de canciones ininterrumpidas.
     * @return Canciones hasta el momento.
     */
    public int getCancionesHastaAnuncio(){
        return cancionesSinAnuncio;
    }

    /**
     * Representación en cadena del usuario gratuito.
     */
    @Override
    public String toString() {
        return "UsuarioGratuito{" +
                "anunciosEscuchados=" + anunciosEscuchados +
                ", ultimoAnuncio=" + ultimoAnuncio +
                ", reproduccionesHoy=" + reproduccionesHoy +
                ", limiteReproducciones=" + limiteReproducciones +
                ", cancionesSinAnuncio=" + cancionesSinAnuncio +
                ", fechaUltimaReproduccion=" + fechaUltimaReproduccion +
                ", LIMITE_DIARIO=" + LIMITE_DIARIO +
                ", CANCIONES_ENTRE_ANUNCIOS=" + CANCIONES_ENTRE_ANUNCIOS +
                '}';
    }
}
