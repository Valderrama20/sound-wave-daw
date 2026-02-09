package enums;

/**
 * Enum que define los algoritmos de recomendación disponibles para sugerir contenido.
 */
public enum AlgoritmoRecomendacion {
    COLABORATIVO("Basado en usuarios similares"),
    CONTENIDO("Basado en características del contenido"),
    HIBRIDO("Combinación de ambos");

    private final String descripcion;

    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }

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
