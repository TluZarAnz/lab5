package exceptions;

public class ArgumentNeeded extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    public ArgumentNeeded(String message){
        super(message);
    }
}

