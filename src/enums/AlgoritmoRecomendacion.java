package enums;

public enum AlgoritmoRecomendacion {
    COLABORATIVO("Basado en usuarios similares"),
    CONTENIDO("Basado en características del contenido"),
    HIBRIDO("Combinación de ambos");

    // Atributos
    private final String descripcion;

    // Constructor
    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodos
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "AlgoritmoRecomendacion{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
