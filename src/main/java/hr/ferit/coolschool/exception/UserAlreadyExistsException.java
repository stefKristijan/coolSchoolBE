package hr.ferit.coolschool.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
