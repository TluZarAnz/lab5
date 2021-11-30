package exceptions;

public class ValueTooLowException extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    public ValueTooLowException(String message){
        super(message);
    }
}
