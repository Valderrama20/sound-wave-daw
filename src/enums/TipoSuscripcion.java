package enums;

public enum TipoSuscripcion {
    GRATUITO(0.0, false, 50, false),
    PREMIUM(9.99, true, -1, true),
    FAMILIAR(14.99, true, -1, true),
    ESTUDIANTE(4.99, true, -1, true);

    // Atributos
    private final double precioMensual;
    private final boolean sinAnuncios;
    private final int limiteReproducciones;
    private final boolean descargasOffline;

    // Constructor
    TipoSuscripcion(double precioMensual, boolean sinAnuncios, int limiteReproducciones, boolean descargasOffline) {
        this.precioMensual = precioMensual;
        this.sinAnuncios = sinAnuncios;
        this.limiteReproducciones = limiteReproducciones;
        this.descargasOffline = descargasOffline;
    }

    // Metodos
    public double getPrecioMensual() {
        return precioMensual;
    }

    public boolean isSinAnuncios() {
        return sinAnuncios;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public boolean tieneReproduccionesIlimitadas() {
        return limiteReproducciones == -1;
    }

    @Override
    public String toString() {
        return "TipoSuscripcion{" +
                "nombre=" + name() +
                "precioMensual=" + precioMensual +
                '}';
    }
}
