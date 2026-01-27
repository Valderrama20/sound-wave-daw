package usuarios;

import enums.TipoSuscripcion;

import java.util.Date;

public class UsuarioGratuito extends Usuario{

    // Atributos
    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;

    // Constructor
    public UsuarioGratuito(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, Date fechaRegisto, Plataforma plataforma, int anunciosEscuchados, Date ultimoAnuncio, int reproduccionesHoy, int limiteReproducciones) {
        super(id, nombre, email, password, suscripcion, fechaRegisto, plataforma);
        this.anunciosEscuchados = anunciosEscuchados;
        this.ultimoAnuncio = ultimoAnuncio;
        this.reproduccionesHoy = reproduccionesHoy;
        this.limiteReproducciones = limiteReproducciones;
    }

    // Getters and setters
    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public void setAnunciosEscuchados(int anunciosEscuchados) {
        this.anunciosEscuchados = anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public void setUltimoAnuncio(Date ultimoAnuncio) {
        this.ultimoAnuncio = ultimoAnuncio;
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

    public void setLimiteReproducciones(int limiteReproducciones) {
        this.limiteReproducciones = limiteReproducciones;
    }

    // Metodos
    @Override
    public void reproducir(Contenido contenido) {
        // TODO
    }

    public void verAnuncio(){
        // TODO
    }

    public boolean puedeReproducir(){
        return reproduccionesHoy < limiteReproducciones;
    }

    public void reiniciarContadorDiario(){
        reproduccionesHoy = 0;
    }

}
