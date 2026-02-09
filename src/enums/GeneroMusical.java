package enums;

/**
 * Enum que representa los géneros musicales disponibles para clasificar canciones.
 */
public enum GeneroMusical {
    POP("Pop", "Música popular contemporánea"),
    ROCK("Rock", "Rock clásico y moderno"),
    HIPHOP("Hip Hop", "Hip hop y rap"),
    JAZZ("Jazz", "Jazz clásico y contemporáneo"),
    ELECTRONICA("Electrónica", "Música electrónica y EDM"),
    REGGAETON("Reggaetón", "Reggaetón y música urbana latina"),
    INDIE("Indie", "Música independiente"),
    CLASICA("Clásica", "Música clásica"),
    COUNTRY("Country", "Música country"),
    METAL("Metal", "Heavy metal y subgéneros"),
    RNB("R&B", "Rhythm and Blues"),
    SOUL("Soul", "Música soul"),
    BLUES("Blues", "Blues clásico y contemporáneo"),
    TRAP("Trap", "Trap y música urbana");

    private final String nombre;
    private final String description;

    GeneroMusical(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return nombre;
    }


}
