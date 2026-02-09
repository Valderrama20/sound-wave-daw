package excepciones.usuario;

/**
 * Excepción lanzada cuando la contraseña proporcionada no cumple con los requisitos mínimos de seguridad.
 */
public class PasswordDebilException extends Exception{

    public PasswordDebilException() {
    }

    public PasswordDebilException(String message) {
        super(message);
    }
}
