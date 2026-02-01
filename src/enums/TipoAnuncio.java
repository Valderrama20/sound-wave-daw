package enums;

public enum TipoAnuncio {
    AUDIO(15, 0.05),
    BANNER(0, 0.02),
    VIDEO(30, 0.10);

    // Atributos
    private final int duracionSegundos;
    private final double costoPorImpresion;

    // Constructor
    TipoAnuncio(int duracionSegundos, Double costoPorImpresion){
        this.duracionSegundos = duracionSegundos;
        this.costoPorImpresion = costoPorImpresion;
    }

    // Metodos
    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public double getCostoPorImpresion() {
        return costoPorImpresion;
    }

    @Override
    public String toString() {
        return "TipoAnuncio{" +
                "duracionSegundos=" + duracionSegundos +
                ", costoPorImpresion=" + costoPorImpresion +
                '}';
    }
}
