package excepciones.plataforma;

/**
 * Excepción lanzada cuando se intenta registrar un usuario con credenciales ya existentes.
 */
public class UsuarioYaExisteException extends Exception {

    public UsuarioYaExisteException() {
    }

    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
