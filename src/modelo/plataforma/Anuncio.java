package modelo.plataforma;

import enums.TipoAnuncio;

import java.util.Objects;
import java.util.UUID;

public class Anuncio {

    // Atributos
    private String id;
    private String empresa;
    private int duracionSegundos;
    private String audioURL;
    private TipoAnuncio tipo;
    private int impresiones = 0;
    private double presupuesto;
    private boolean activo;

    // Constructor
    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto, String audioURL) {
        this.id = UUID.randomUUID().toString();
        this.empresa = empresa;
        this.tipo = tipo;
        this.presupuesto = presupuesto;
        this.audioURL = audioURL;
    }

    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto) {
        this(empresa, tipo, presupuesto, null);
    }

    // Getters And Setters
    public String getId() {
        return id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public int getImpresiones() {
        return impresiones;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Metodos
    public void reproducir() {
        if(isActivo()) {
            System.out.println("Reproduciendo anuncio...");
        }
    }

    public void registrarImpresion() {
        impresiones++;
        presupuesto -= tipo.getCostoPorImpresion();

        if(presupuesto <= 0) {
            activo = false;
        }
    }

    public double calcularCostoPorImpresion() {
        return  tipo.getCostoPorImpresion();
    }

    public double calcularCostoTotal() {
        return impresiones * tipo.getCostoPorImpresion();
    }

    public int calcularImpresionesRestantes() {
        return (int) (presupuesto / tipo.getCostoPorImpresion());
    }

    public void desactivar() {
        setActivo(false);
    }

    public void activar() {
        setActivo(true);
    }

    public boolean puedeMostrarse() {
        return activo && presupuesto > 0;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id='" + id + '\'' +
                ", empresa='" + empresa + '\'' +
                ", duracionSegundos=" + duracionSegundos +
                ", audioURL='" + audioURL + '\'' +
                ", tipo=" + tipo +
                ", impresiones=" + impresiones +
                ", presupuesto=" + presupuesto +
                ", activo=" + activo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Anuncio anuncio = (Anuncio) o;
        return duracionSegundos == anuncio.duracionSegundos && impresiones == anuncio.impresiones && Double.compare(presupuesto, anuncio.presupuesto) == 0 && activo == anuncio.activo && Objects.equals(id, anuncio.id) && Objects.equals(empresa, anuncio.empresa) && Objects.equals(audioURL, anuncio.audioURL) && tipo == anuncio.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empresa, duracionSegundos, audioURL, tipo, impresiones, presupuesto, activo);
    }
}
