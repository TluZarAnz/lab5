package commands;
import java.io.IOException;

public  abstract class Command  {
    String name;
    String description;

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */

    protected abstract void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException;

    /**
     * @param name
     * @param description
     */
    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
