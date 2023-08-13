package exceptions;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthentificationException extends RuntimeException{
    public AuthentificationException(String message){
        super(message);
    }
}
