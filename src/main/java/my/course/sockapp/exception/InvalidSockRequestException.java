package my.course.sockapp.exception;

public class InvalidSockRequestException extends RuntimeException {
    public InvalidSockRequestException(String message){
        super(message);
    }
}
