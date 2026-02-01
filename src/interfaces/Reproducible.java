package interfaces;

/**
 * Contrato para contenido que se puede reproducir (control básico de reproducción).
 */
public interface Reproducible {

    /**
     * Inicia la reproducción del contenido.
     */
    void play();

    /**
     * Pausa la reproducción del contenido
     */
    void pause();

    /**
     * Detiene completamente la reproducción del contenido.
     */
    void stop();

    /**
     * Devuelve la duración del contenido en segundos
     *
     * @return duracion en segundos
     */
    int getDuracion();
}
