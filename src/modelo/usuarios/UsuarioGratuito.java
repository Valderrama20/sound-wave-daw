package modelo.usuarios;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import enums.TipoSuscripcion;
import modelo.plataforma.Anuncio;

import java.util.Date;

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

    // Constructor
    public UsuarioGratuito(String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException {
        super( nombre, email, password, TipoSuscripcion.GRATUITO);
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
        contenido.reproducir();
    }

    public void verAnuncio(Anuncio anuncio) {
        // TODO
        ultimoAnuncio = new Date();
        System.out.println("Viendo un anuncio...");
    }

    public boolean puedeReproducir() {
        return reproduccionesHoy < limiteReproducciones;
    }

    public boolean debeVerAnuncio(){
        return anunciosEscuchados % (CANCIONES_ENTRE_ANUNCIOS + 1) == 0;
    }

    public void reiniciarContadorDiario() {
        reproduccionesHoy = 0;
    }

    public int getReproduccionesRestantes() {
        return limiteReproducciones - reproduccionesHoy;
    }

    public int getCancionesHastaAnuncio(){
        return CANCIONES_ENTRE_ANUNCIOS - (anunciosEscuchados % CANCIONES_ENTRE_ANUNCIOS);
    }

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
