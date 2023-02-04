package my.course.sockapp.exception;

public class InSufficientSockQuantity extends RuntimeException{

    public InSufficientSockQuantity(String message) {
        super(message);
    }
}
