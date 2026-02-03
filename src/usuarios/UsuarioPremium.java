package usuarios;

import modelo.contenido.Contenido;
import enums.TipoSuscripcion;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioPremium extends Usuario{

    // Atributos
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    // Constructor
     public UsuarioPremium(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, Date fechaRegisto, Plataforma plataforma, boolean descargasOffline, int maxDescargas, String calidadAudio) {
         super(id, nombre, email, password, suscripcion, fechaRegisto, plataforma);
         this.descargasOffline = descargasOffline;
         this.maxDescargas = maxDescargas;
         this.descargados = new ArrayList<>();
         this.calidadAudio = calidadAudio;
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

    public void setMaxDescargas(int maxDescargas) {
        this.maxDescargas = maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return descargados;
    }

    public void addDescargado(Contenido descargado) {
        this.descargados.add(descargado);
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    // Metodos
    @Override
    public void reproducir(Contenido contenido) {
        contenido.reproducir();
    }

    public void descargar(Contenido contenido) {
         descargados.add(contenido);
    }

    public void eliminarDescarga(Contenido contenido) {
         descargados.remove(contenido);
    }

    public boolean verificarEspacioDescarga(){
         return descargados.size() < maxDescargas;
    }
}
